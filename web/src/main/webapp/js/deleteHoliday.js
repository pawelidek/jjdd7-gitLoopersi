$(function () {
  $(document).ready(function () {
    $(".delete-holiday").click(function (event) {

        var buttonId = $(event.target).attr('data-id');
        $.ajax({
          url: "/admin/holiday",
          method: "DELETE",
          data: {id: buttonId},
          success: function () {
            alert('Holiday has been deleted');
          },
          error: function (error) {
            alert(buttonId);
          }
        });
    });
  });
});