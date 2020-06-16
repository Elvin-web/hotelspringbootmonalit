function addFloor() {
    var name = $('#text-input').val();
    var floorNumber = $('#text-input1').val();
    var description = $('#textarea-input').val();
    var action1 = $('#checkbox1').val();
    var data = {
        name: name,
        floorNumber: floorNumber,
        description: description,
        action1: action1
    };
    $.ajax({
        url: 'addFloor',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Floor has been successfully added');
                window.location.href='/hotelMonalit/getFloorList';
            } else {
                alert('Problem! Floor has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function updateFloor(floorId) {
    var name = $('#text-inputU').val();
    var floorNumber = $('#text-input1U').val();
    var description = $('#textarea-inputU').val();
    var action1 = $('#checkbox1U').val();
    var data = {
        name: name,
        floorNumber: floorNumber,
        description: description,
        action1: action1,
        floorId: floorId
    };
    $.ajax({
        url: 'updateFloor',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Floor has been successfully updated');
                window.location.href='/hotelMonalit/getFloorList';
            } else {
                alert('Problem! Floor has not been successfully updated!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function deleteFloor(floorId, name) {
    var isDelete = confirm("Are you sure to delete " + name + " ?");
    if (isDelete) {
        $.ajax({
            url: 'deleteFloor',
            type: 'POST',
            data: 'floorId=' + floorId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Floor has been successfully deleted');
                    window.location.href='/hotelMonalit/getFloorList';
                } else {
                    alert('Problem! Floor has not been successfully deleted!');
                }
            }
        });
    }
}
$(function () {

    $('#floorSubmitId').click(function () {
        addFloor();
    });
    $('#floorSubmitIdU').click(function () {
        updateFloor();
    });

});