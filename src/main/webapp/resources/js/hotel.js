/*function addHotel() {
    var name = $('#text-input').val();
    var logo = $('#file-input').val();
    var address = $('#text-input3').val();
    var phone = $('#text-input2').val();
    var email = $('#email-input').val();
    var star = $('#select').val();
    var data = {
        name: name,
        address: address,
        phone: phone,
        email: email,
        star: star
    };
    $.ajax({
        url: 'addHotel',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Hotel has been successfully added');
                window.location.href='/hotel/hs?action=getHotelList';
            } else {
                alert('Problem! Hotel has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
*/
function updateHotel() {
    var name = $('#text-inputU').val();
    var logo = $('#file-inputU').val();
    var address = $('#text-input3U').val();
    var phone = $('#text-input2U').val();
    var email = $('#email-inputU').val();
    var star = $('#selectU').val();
    var data = {
        name: name,
        address: address,
        phone: phone,
        email: email,
        star: star
    };
    $.ajax({
        url: 'hs?action=updateHotel',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Hotel has been successfully updated');
                window.location.href='/hotel/hs?action=getHotelList';
            } else {
                alert('Problem! Hotel has not been successfully updated!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function deleteHotel(hotelId, name) {
    var isDelete = confirm("Are you sure to delete " + name + " ?");
    if (isDelete) {
        $.ajax({
            url: 'deleteHotel',
            type: 'POST',
            data: 'hotelId=' + hotelId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Hotel has been successfully deleted');
                    window.location.href='/hotelMonalit/getHotelList';
                } else {
                    alert('Problem! Hotel has not been successfully deleted!');
                }
            }
        });
    }
}



$(function () {

  /*  $('#hotelSubmitId').click(function () {
        addHotel();
    });*/
    $('#hotelSubmitIdU').click(function () {
        updateHotel();
    });

});