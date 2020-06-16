function addCountry() {
    var name = $('#text-input').val();
    var sortName = $('#text-input1').val();
    var data = {
        name: name,
        sortName: sortName
    };
    $.ajax({
        url: 'addCountry',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Country has been successfully added');
                window.location.href='/hotelMonalit/getCountryList';
            } else {
                alert('Problem! Country has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function updateCountry(countryId) {
    var name = $('#text-inputU').val();
    var sortName = $('#text-input1U').val();
    var data = {
        name: name,
        sortName: sortName,
        countryId:countryId
    };
    $.ajax({
        url: 'updateCountry',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Country has been successfully updated');
                window.location.href='/hotelMonalit/getCountryList';
            } else {
                alert('Problem! Country has not been successfully updated!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function deleteCountry(countryId, name) {
    var isDelete = confirm("Are you sure to delete " + name + " ?");
    if (isDelete) {
        $.ajax({
            url: 'deleteCountry',
            type: 'POST',
            data: 'countryId=' + countryId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Country has been successfully deleted');
                    window.location.href='/hotelMonalit/getCountryList';
                } else {
                    alert('Problem! Country has not been successfully deleted!');
                }
            }
        });
    }
}


$(function () {
    $('#countrySubmitId').click(function () {
        addCountry();
    });
    $('#countrySubmitIdU').click(function () {
        updateCountry();
    });

});