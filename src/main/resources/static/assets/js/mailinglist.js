var MailingList = {

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
        }, 1500);
    },

    init: function(){
        $("#emailSubscriptionButton").click(function () {

            var messageSpace = $("#emailSubscriptionMessage");
            var emailTextField = $("#emailSubscriptionTextfield");

            var userEmail = emailTextField.val();
            if (MailingList.validateEmail(userEmail) == false) {
                messageSpace.text("Email invalide");
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
        });
    }
    
};

$(function () {
   MailingList.init();
});