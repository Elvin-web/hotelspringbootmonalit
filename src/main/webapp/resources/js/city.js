function addCity() {
    var countryId = $('#select').val();
    var name = $('#nameId').val();
    var data = {
        countryId: countryId,
        name: name
    };
    $.ajax({
        url: 'addCity',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('City has been successfully added');
                window.location.href='/hotelMonalit/getCityList';
            } else {
                alert('Problem! City has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function updateCity(cityId) {
    var countryId = $('#selectU').val();
    var name = $('#nameIdU').val();
    var data = {
        countryId: countryId,
        name: name,
        cityId: cityId
    };
    $.ajax({
        url: 'updateCity',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('City has been successfully updated');
                window.location.href='/hotelMonalit/getCityList';
            } else {
                alert('Problem! City has not been successfully updated!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function deleteCity(cityId, name) {
    var isDelete = confirm("Are you sure to delete " + name + " ?");
    if (isDelete) {
        $.ajax({
            url: 'deleteCity',
            type: 'POST',
            data: 'cityId=' + cityId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('City has been successfully deleted');
                    window.location.href='/hotelMonalit/getCityList';
                } else {
                    alert('Problem! City has not been successfully deleted!');
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
    $('#citySubmitId').click(function () {
        addCity();
    });


});