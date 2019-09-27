<article id="contentView">

  <div class="container-fluid">
    <table class="table table-borderless">
      <tr>
        <td style="text-align: left"><h2>Load Holidays from JSON File</h2></td>
      </tr>
    </table>
  </div>

  <form method="POST" enctype="multipart/form-data" action="/admin/jsonLoader">
    <div class="input-group mb-3">
      <table class="table table-borderless">
        <tr>
          <td style="width: 95%">
            <div class="custom-file">
              <input type="file" name="file" class="custom-file-input" id="inputGroupFile03"
                     aria-describedby="inputGroupFileAddon03">
              <label class="custom-file-label" for="inputGroupFile03">Choose file</label>
            </div>
          </td>
          <td style="width: 5%">
            <div class="input-group-prepend">
              <button class="btn btn-outline-secondary" type="submit" id="inputGroupFileAddon03">
                Upload
              </button>
            </div>
          </td>
        </tr>
      </table>
    </div>
  </form>
</article>
<script src="/js/upload-form.js"></script>
