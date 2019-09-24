$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').trigger('focus')
});

$(function () {
  $(document).ready(function () {

    let errorsTag = $("#errors");
    errorsTag.empty();
    errorsTag.hide();

    $(".edit-team").click(function (event) {

      var buttonId = $(this).attr('data-id');
      $.ajax({
        url: '/api/admin/team/id/' + buttonId,
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

    let errorsTag = $("#errors");
    errorsTag.empty();
    errorsTag.hide();

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

      let errorsTag = $("#errors");
      errorsTag.empty();
      errorsTag.hide();

      $.ajax({
        url: "/api/admin/team",
        method: $('#formMethod').val(),
        data: $('form#settingForm').serialize(),
        success: function () {
          location.reload();
        },
        error: function (error) {
          var errors = JSON.parse(error.responseText);
          var errorsHtml = "";
          for (var i = 0; i < errors.length; i++) {
            errorsHtml += "<strong>" + errors[i] + "</strong><br/>"
          }
          errorsTag.html(errorsHtml);
          errorsTag.show();
        }
      });
    });
  });
});

$(function () {

  $(document).ready(function () {
    $(".delete-team").click(function () {

      let errorsTag = $("#errorsMain");
      errorsTag.empty();
      errorsTag.hide();

      var teamId = $(this).attr('data-id');

      $.ajax({
        url: '/api/admin/team?id=' + teamId,
        type: 'DELETE',
        success: function (result) {
          $('#team' + teamId).remove();
        },
        error: function (error) {
          var errors = JSON.parse(error.responseText);
          var errorsHtml = "";
          for (var i = 0; i < errors.length; i++) {
            errorsHtml += "<strong>" + errors[i] + "</strong><br/>"
          }

          errorsTag.html(errorsHtml);
          errorsTag.show();
        },
        window:setTimeout(function () {
          errorsTag.fadeTo(500, 0).slideUp(500, function () {
            $(this).remove();
          });
        }, 1500)
      });
    });
  });
});

window.setTimeout(function () {
  $(".alert-success").fadeTo(500, 0).slideUp(500, function () {
    $(this).remove();
  });
}, 1500);