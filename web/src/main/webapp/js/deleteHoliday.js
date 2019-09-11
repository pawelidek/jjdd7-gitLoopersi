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
            location.reload();
          },
          error: function (error) {
            alert(buttonId);
          }
        });
    });
  });
});
$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').trigger('focus')
})

$(function () {
  $(document).ready(function () {
    $("#add_holiday").click(function (event) {
      $.ajax({
        url: "/admin/holiday",
        method: "POST",
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
    $(".update-holiday").click(function (event) {

      var buttonId = $(event.target).attr('data-id');
      $.ajax({
        url: "/admin/holiday/edit/?id=" + $(this).attr('data-id'),
        method: "GET",
        success: function () {
          $('#editModal').modal('toggle');
        },
        error: function (error) {
          alert(buttonId);
        }
      });
    });
  });
});