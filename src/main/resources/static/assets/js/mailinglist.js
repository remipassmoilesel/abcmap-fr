var MailingList = {

    _formEnabled : true,

    validateEmail: function(email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    },

    clearForm : function() {

        var messageSpace = $("#emailSubscriptionMessage");
        var emailTextField = $("#emailSubscriptionTextfield");

        setTimeout(function () {
            messageSpace.text("");
            emailTextField.val("");
            MailingList._formEnabled = true;
        }, 1500);
    },

    suscribe: function(){

        if(!MailingList._formEnabled){
            return;
        }
        MailingList._formEnabled = false;

        var messageSpace = $("#emailSubscriptionMessage");
        var emailTextField = $("#emailSubscriptionTextfield");

        var userEmail = emailTextField.val();
        if (MailingList.validateEmail(userEmail) == false) {
            messageSpace.text("Email invalide");
            MailingList._formEnabled = true;
            return;
        }

        $.post(UrlTree.POST_MAIL_URL, {
            email: userEmail
        }).done(function () {
            messageSpace.text('Merci 👍 ');
            MailingList.clearForm();
        }).fail(function () {
            messageSpace.text('Merci 👍 !');
            MailingList.clearForm();
            console.error(arguments);
        });
    },

    init: function(){
        $("#emailSubscriptionButton").click(function () {
            MailingList.suscribe();
        });
    }
    
};

$(function () {
   MailingList.init();
});