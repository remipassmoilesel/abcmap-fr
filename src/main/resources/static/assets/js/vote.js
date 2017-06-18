var Vote = {

    _setVoteValue: function (value) {
        // display stars
        $(".voteStar").each(function () {
            var val = $(this).data("value");
            if (val <= value) {
                $(this).removeClass("fa-star-o");
                $(this).addClass("fa-star");
            }

            else {
                $(this).addClass("fa-star-o");
                $(this).removeClass("fa-star");
            }
        });
    },

    clearVoteForm: function () {
        setTimeout(function () {
            Vote._setVoteValue(0);
            $("#voteMessage").text("");
        }, 1500);
    },

    vote: function (value) {

        var self = Vote;
        var messageSpace = $("#voteMessage");

        try {

            // show value
            self._setVoteValue(value);

            // send it and clear after
            $.get(UrlTree.VOTE_URL + "?v=" + value + "&p=" + document.location)
                .done(function () {
                    messageSpace.text('Merci 👍 ');
                    self.clearVoteForm();
                }).fail(function () {
                messageSpace.text('Merci 👍 !');
                self.clearVoteForm();
                console.error(arguments);
            });

        } catch (e) {
            console.error(e);
        }

    }
};