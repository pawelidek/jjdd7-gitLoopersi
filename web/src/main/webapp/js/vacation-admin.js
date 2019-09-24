$(function () {
  $(document).ready(function () {

    let errorsTag = $("#errors");
    errorsTag.empty();
    errorsTag.hide();

    $(".reject-vacation").click(function (event) {
      var buttonId = $(this).attr('data-id');
      $.ajax({
        url: '/api/admin/vacation/reject/' + buttonId,
        method: "PUT",
        success: function () {
        },
        error: function (error) {
          alert(buttonId);
        }
      });
    });
  });
});

$(function () {
  $(document).ready(function () {

    let errorsTag = $("#errors");
    errorsTag.empty();
    errorsTag.hide();

    $(".accept-vacation").click(function (event) {
      var buttonId = $(this).attr('data-id');
      $.ajax({
        url: '/api/admin/vacation/accept/' + buttonId,
        method: "PUT",
        success: function () {
        },
        error: function (error) {
          alert(buttonId);
        }
      });
    });
  });
});