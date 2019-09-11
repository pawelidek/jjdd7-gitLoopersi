$(function () {
  $(document).ready(function () {
    $("#settingButton").click(function (event) {
      $.ajax({
        url: "/admin/employee",
        method: $('#setEmployeeMethod').val(),
        data: $('form#contactForm').serialize(),
        success: function () {
          location.reload();
        },
        error: function (error) {
          alert('Error! Holiday has not been added. Check data in your form');
        }
      });
    });
  });
});

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

$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').trigger('focus')
});
