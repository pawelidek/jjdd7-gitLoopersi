$(function () {
  $(document).ready(function () {
    $(".reject-vacation").click(function () {

      var vacationId = $(this).attr('data-id');

      $.ajax({
        url: '/admin/vacation?id=' + vacationId,
        type: 'DELETE',
        success: function (result) {
          $('#vacation' + vacationId).remove();
        }
      });
    });
  });
});