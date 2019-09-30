$(function () {
  $(document).ready(function () {

    $(".reject-vacation").click(function (event) {

      let successTag = $("#successMain");
      successTag.empty();
      successTag.hide();

      var buttonId = $(this).attr('data-id');
      $.ajax({
        url: '/api/admin/vacation/reject/' + buttonId,
        method: "PUT",
        success: function (success) {

          var successesHtml = "";
          for (let i = 0; i < success.length; i++) {
            successesHtml += "<strong>" + success[i] + "</strong><br/>";
          }

          $('.status-type-' + buttonId).text("REJECTED");
          successTag.html(successesHtml);
          successTag.show();

          setTimeout(function () {
            successTag.fadeTo(500, 0).slideUp(500, function () {
              successTag.hide();
              successTag.css('opacity', 1);
            });
          }, 1500)
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
    $(".accept-vacation").click(function (event) {

      let successTag = $("#successMain");
      successTag.empty();
      successTag.hide();

      var buttonId = $(this).attr('data-id');
      $.ajax({
        url: '/api/admin/vacation/accept/' + buttonId,
        method: "PUT",
        success: function (success) {

          var successesHtml = "";
          for (let i = 0; i < success.length; i++) {
            successesHtml += "<strong>" + success[i] + "</strong><br/>";
          }

          $('.status-type-' + buttonId).text("ACCEPTED");
          successTag.html(successesHtml);
          successTag.show();

          setTimeout(function () {
            successTag.fadeTo(500, 0).slideUp(500, function () {
              successTag.hide();
              successTag.css('opacity', 1);
            });
          }, 1500)
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
    $("#searchByDates1").click(function (event) {
      let dateFrom = $('#dateFrom1').val();
      let dateTo = $('#dateTo1').val();
      $.ajax({
        url: '/admin/vacation?dateFrom=' + dateFrom + '&dateTo='
            + dateTo,
        type: 'GET',
        success: function () {
          window.location.href = '/admin/vacation?dateFrom=' + dateFrom
              + '&dateTo='
              + dateTo
          ;
        }
      });
    });
  });
});