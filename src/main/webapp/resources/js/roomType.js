function addRoomType() {
    var name = $('#text-input').val();
    var slug = $('#text-input1').val();
    var shortCode = $('#text-input2').val();
    var baseOccupancy = $('#text-input3').val();
    var higherOccupancy = $('#text-input4').val();
    var kidsOccupancy = $('#text-input5').val();
    var basePice = $('#text-input6').val();
    var additionalPrice = $('#text-input7').val();
    var extraBedPrice = $('#text-input8').val();
    var description = $('#textarea-input').val();
    var extraBed = $('#checkbox1').val();
    var image = $('#file-input').val();
    var data = {
        name: name,
        slug: slug,
        shortCode: shortCode,
        baseOccupancy: baseOccupancy,
        higherOccupancy: higherOccupancy,
        kidsOccupancy: kidsOccupancy,
        basePice: basePice,
        additionalPrice: additionalPrice,
        extraBedPrice: extraBedPrice,
        description: description,
        image: image,
        extraBed: extraBed
    };
    $.ajax({
        url: 'rts?action=addRoomType1',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Room type has been successfully added');
                window.location.href='/hotel/rts?action=getRoomTypeList';
            } else {
                alert('Problem! Room type has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function updateRoomType(roomTypeId) {
    var name = $('#text-inputU').val();
    var slug = $('#text-input1U').val();
    var shortCode = $('#text-input2U').val();
    var baseOccupancy = $('#text-input3U').val();
    var higherOccupancy = $('#text-input4U').val();
    var kidsOccupancy = $('#text-input5U').val();
    var basePice = $('#text-input6U').val();
    var additionalPrice = $('#text-input7U').val();
    var extraBedPrice = $('#text-input8U').val();
    var description = $('#textarea-inputU').val();
    var extraBed = $('#checkbox1U').val();
    var image = $('#file-inputU').val();
    var data = {
        name: name,
        slug: slug,
        shortCode: shortCode,
        baseOccupancy: baseOccupancy,
        higherOccupancy: higherOccupancy,
        kidsOccupancy: kidsOccupancy,
        basePice: basePice,
        additionalPrice: additionalPrice,
        extraBedPrice: extraBedPrice,
        description: description,
        image: image,
        extraBed: extraBed,
        roomTypeId:roomTypeId
    };
    $.ajax({
        url: 'rts?action=updateRoomType',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Room type has been successfully updated');
                window.location.href='/hotel/rts?action=getRoomTypeList';
            } else {
                alert('Problem! Room type has not been successfully updated!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function deleteRoomType(roomTypeId, name) {
    var isDelete = confirm("Are you sure to delete " + name + " ?");
    if (isDelete) {
        $.ajax({
            url: 'deleteRoomType',
            type: 'POST',
            data: 'roomTypeId=' + roomTypeId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Room type has been successfully deleted');
                    window.location.href='/hotelMonalit/getRoomTypeList';
                } else {
                    alert('Problem! Room type has not been successfully deleted!');
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
    $('#roomTypeSubmitId').click(function () {
        addRoomType();
    });
    $('#roomTypeSubmitIdU').click(function () {
        updateRoomType();
    });

});