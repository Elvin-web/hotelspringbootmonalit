function addAmenities() {
    var name = $('#nameId').val();
    var description = $('#descriptionId').val();
    var action1 = $('#action1').val();
    var image = $('#file-input').val();
    var data = {
        name: name,
        description: description,
        action1: action1,
        image: image
    };
    $.ajax({
        url: 'as?action=addAmenities1',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Amenities has been successfully added');
                window.location.href='/hotel/as?action=getAmenitiesList';
            } else {
                alert('Problem! Amenities has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function editAmenities(amenitiesId) {
    globAmenitiesId = amenitiesId;
    $.ajax({
        url: 'as?action=editAmenities',
        type: 'GET',
        dataType: 'html',
        data: 'amenitiesId' + amenitiesId,
        success: function (response) {
            // response.load('WEB-INF/pages/editAmenities.jsp');
            $('WEB-INF/pages/editAmenities.jsp').html(response);
            /* $('#editAmenitiesDialogId').html(response);
             $('#editAmenitiesDialogId').dialog('open');*/
        }
    });
}

function updateAmenities(amenitiesId) {
    var name = $('#nameIdU').val();
    var description = $('#descriptionIdU').val();
    var action1 = $('#action1U').val();
    var image = $('#file-inputU').val();
    var data = {
        name: name,
        description: description,
        action1: action1,
        image: image,
        amenitiesId: amenitiesId
    };
    $.ajax({
        url: 'as?action=updateAmenities',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Amenities has been successfully updated');
                window.location.href='/hotel/as?action=getAmenitiesList';
            } else {
                alert('Problem! Amenities has not been successfully updated!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function deleteAmenities(amenitiesId, name) {
    var isDelete = confirm("Are you sure to delete " + name + " ?");
    if (isDelete) {
        $.ajax({
            url: 'deleteAmenities',
            type: 'POST',
            data: 'amenitiesId=' + amenitiesId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Amenities has been successfully deleted!');
                    window.location.href='/hotelMonalit/getAmenitiesList';
                } else {
                    alert('Amenities has not been successfully deleted!');
                }
            }
        });
    }
}
$(function () {
    // $("[data-toggle=tooltip]").tooltip();
    $('#closeId').click(function () {
        $('#successMessage').dialog("close");
    });
    $('#closeIdE').click(function () {
        $('#errorMessage').dialog("close");
    });
    $('#amenitiesSubmitId').click(function () {
        addAmenities();
    });
    $('#amenitiesSubmitIdU').click(function () {
        updateAmenities();
    });

});