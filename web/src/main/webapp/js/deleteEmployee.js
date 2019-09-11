$(function () {

  $(document).ready(function () {

    $(".delete-employee-link").click(function () {

      $.ajax({
        url: '/admin/employee?id=' + $(this).attr('data-id'),
        type: 'DELETE',
        success: function (result) {
          location.reload();
        }
      });
    });

  });
});