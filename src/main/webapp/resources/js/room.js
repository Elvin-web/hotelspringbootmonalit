function addDictionary() {
    var roomType = $('#select1').val();
    var room = $('#text-input').val();
    var floor = $('#select').val();
    var amenities = $('#multiple-select').val();
    if ($("#multiple-select").find('option').not(':selected').length > 0) { var notSelected = $("#multiple-select").find('option').not(':selected');
        var amenitiesUnselect = notSelected.map(function () { return this.value; }).get(); }
    var data = {
        room: room,
        floor: floor,
        amenities: amenities,
        roomType: roomType,
        amenitiesUnselect: amenitiesUnselect
    };
    $.ajax({
        url: 'addRoom',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Room has been successfully added');
                window.location.href = '/hotelMonalit/getRoomList';
            } else {
                alert('Problem! Room has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function updateDictionary(id_room) {
   // var id_room = $('#text-input5U').val();
    var roomType = $('#select1U').val();
    var room = $('#text-inputU').val();
    var floor = $('#selectU').val();
    var amenities = $('#multiple-selectU').val();
    if ($("#multiple-selectU").find('option').not(':selected').length > 0) { var notSelected = $("#multiple-selectU").find('option').not(':selected');
        var amenitiesUnselect = notSelected.map(function () { return this.value; }).get(); }
    var data = {
        //roomId: roomId,
        id_room:id_room,
        room: room,
        floor: floor,
        amenities: amenities,
        roomType: roomType,
        amenitiesUnselect: amenitiesUnselect

    };
    $.ajax({
        url: 'updateRoom',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Room has been successfully updated');
                window.location.href = '/hotelMonalit/getRoomList';
            } else {
                alert('Problem! Room has not been successfully updated!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function deleteDictionary(dictionaryId, name) {
    var isDelete = confirm("Are you sure to delete " + name + " ?");
    if (isDelete) {
        $.ajax({
            url: 'deleteRoom',
            type: 'POST',
            data: 'dictionaryId=' + dictionaryId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Room has been successfully deleted!');
                    window.location.href = '/hotelMonalit/getRoomList';
                } else {
                    alert('Problem! Room has not been successfully deleted!');
                }
            }
        });
    }
}

$(function () {

    $('#dictionarySubmitId').click(function () {
        addDictionary();
    });
    $('#dictionarySubmitIdU').click(function () {
        updateDictionary();
    });

});