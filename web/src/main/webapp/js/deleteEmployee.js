$(function () {

  $(document).ready(function () {

    $(".delete-employee-link").click(function () {

      $.ajax({
        url: '/admin/user?id=' + $(this).attr('data-id'),
        type: 'DELETE',
        success: function (result) {
          location.reload();
        }
      });
    });

  });
});