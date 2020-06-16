function addPosition() {
    var name = $('#text-input').val();
    var data = {
        name: name
    };
    $.ajax({
        url: 'addPosition',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Position has been successfully added.');
                window.location.href='/hotelMonalit/getPositionList';
            } else {
                alert('Problem! Position has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function updatePosition(positionId) {
    var name = $('#text-inputU').val();
    var data = {
        name: name,
        positionId:positionId
    };
    $.ajax({
        url: 'updatePosition',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Position has been successfully update.');
                window.location.href='/hotelMonalit/getPositionList';
            } else {
                alert('Problem! Position has not been successfully update!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function deletePosition(positionId, positionValue) {
    var isDelete = confirm("Are you sure to delete " + positionValue + " ?");
    if (isDelete) {
        $.ajax({
            url: 'deletePosition',
            type: 'POST',
            data: 'positionId=' + positionId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Position has been successfully deleted.');
                    window.location.href='/hotelMonalit/getPositionList';
                } else {
                    alert('Problem! Position has not been successfully deleted!');
                }
            }
        });
    }
}
$(function () {
    $('#positionSubmitId').click(function () {
        addPosition();
    });
   $('#positionSubmitIdU').click(function () {
        updatePosition(positionId);
    });

});