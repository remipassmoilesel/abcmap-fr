var ContactForm = {

    verified: false,

    init: function () {
        var clickArea = $("#clickArea");
        clickArea.click(function (e) {
            if (e && e.pageX && e.pageY && e.pageX != 0 && e.pageY != 0) {
                ContactForm.verified = true;
                clickArea.css("color", "green");
                clickArea.text("Merci !");
            }
        });
    },

    validateEmail: function (email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    },

    showErrorMessage: function (msg) {
        var messageArea = $(".contactFormErrorMessage");
        messageArea.css('display', 'block');
        messageArea.text(msg);
    },

    submit: function () {

        var self = ContactForm;
        var objectField = $("#object");
        var mailField = $("#email");
        var messageField = $("#message");

        if (!objectField.val() || objectField.val() == "none") {
            self.showErrorMessage("Objet invalide");
            return;
        }

        if (self.validateEmail(mailField.val()) == false) {
            self.showErrorMessage("Email invalide");
            return;
        }

        if (!messageField.val() || messageField.val().length < 50) {
            self.showErrorMessage("Message invalide, 50 caractères minimum");
            return;
        }

        if (!ContactForm.verified) {
            self.showErrorMessage("Veuillez prouver que vous n'êtes pas une machine");
            return;
        }

        $("#contactForm").submit();

    }

};

$(function () {
    ContactForm.init();
});