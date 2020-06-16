function addHousekeepingStatus() {
    var name = $('#text-input').val();
    var description = $('#textarea-input').val();
    var action1 = $('#checkbox1').val();
    var data = {
        name: name,
        description: description,
        action1: action1
    };
    $.ajax({
        url: 'addHousekeepingStatus',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Housekeeping status has been successfully added!');
                window.location.href='/hotelMonalit/getHousekeepingStatusList';
            } else {
                alert('Problem! Housekeeping status has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function updateHousekeepingStatus(housekeepingStatusId) {
    var name = $('#text-inputU').val();
    var description = $('#textarea-inputU').val();
    var action1 = $('#checkbox1U').val();
    var data = {
        name: name,
        description: description,
        action1: action1,
        housekeepingStatusId:housekeepingStatusId
    };
    $.ajax({
        url: 'updateHousekeepingStatus',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Housekeeping status has been successfully update!');
                window.location.href='/hotelMonalit/getHousekeepingStatusList';
            } else {
                alert('Problem! Housekeeping status has not been successfully update!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function deleteHousekeepingStatus(housekeepingStatusId, name) {
    var isDelete = confirm("Are you sure to delete " + name + " ?");
    if (isDelete) {
        $.ajax({
            url: 'deleteHousekeepingStatus',
            type: 'POST',
            data: 'housekeepingStatusId=' + housekeepingStatusId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Housekeeping status has been successfully deleted!');
                    window.location.href='/hotelMonalit/getHousekeepingStatusList';
                } else {
                    alert('Problem! Housekeeping status has not been successfully deleted!');
                }
            }
        });
    }
}
$(function () {
    $('#housekeepingStatusSubmitId').click(function () {
        addHousekeepingStatus();
    });
    $('#housekeepingStatusSubmitIdU').click(function () {
        updateHousekeepingStatus();
    });

});