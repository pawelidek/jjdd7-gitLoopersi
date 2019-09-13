$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').trigger('focus')
});

$(function () {
  $(document).ready(function () {
    $(".edit-team").click(function (event) {

      var buttonId = $(this).attr('data-id');
      $.ajax({
        url: '/api/team/id/' + buttonId,
        method: "GET",
        success: function () {
        },
        error: function (error) {
          alert(buttonId);
        }
      }).done(function (data) {
        $('#id').val(buttonId);
        $('#label').html("Edit team");
        $('#formMethod').val("put");
        $('#name').val(data.name);
        $('#exampleModal').modal('toggle');
      });
    });
  });
});

$(function () {
  $(document).ready(function () {
    $("#add_team_button").click(function (event) {
      $('#id').val("");
      $('#label').html("Add team");
      $('#formMethod').val("post");
      $('#name').val("");
      $('#exampleModal').modal('toggle');
    });
  });
});

$(function () {
  $(document).ready(function () {
    $("#saveTeam").click(function (event) {
      $.ajax({
        url: "/admin/team",
        method: $('#formMethod').val(),
        data: $('form#settingForm').serialize(),
        success: function () {
          location.reload();
        },
        error: function (error) {
          alert('Error! Can\'t save changes. Check form data');
        }
      });
    });
  });
});

$(function () {

  $(document).ready(function () {
    $(".delete-team").click(function () {
      $.ajax({
        url: '/admin/team?id=' + $(this).attr('data-id'),
        type: 'DELETE',
        success: function (result) {
          location.reload();
        }
      });
    });
  });
});
