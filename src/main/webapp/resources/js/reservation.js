function addReservation() {
    var guest = $('#select').val();
    var roomType = $('#select1').val();
    var adults = $('#text-input').val();
    var kids = $('#text-input1').val();
    var checkIn = $('#text-input2').val();
    var checkOut = $('#text-input3').val();
    var extraBed=$('#checkbox1').val();
    var checkIn1 = new Date(checkIn);
    var checkOut1 = new Date(checkOut);
    var Difference_In_Time = checkOut1.getTime() - checkIn1.getTime();
    var night = Difference_In_Time / (1000 * 3600 * 24);
    var data = {
        guest: guest,
        roomType: roomType,
        adults: adults,
        kids: kids,
        checkIn: checkIn,
        checkOut: checkOut,
        extraBed:extraBed,
        night:night
    };
    $.ajax({
        url: 'addReservation',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Reservation has been successfully added');
                window.location.href = '/hotelMonalit/getReservationList';
            } else {
                alert('Problem! Reservation has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function updateReservation(reservationId) {
    var guest = $('#selectU').val();
    var roomType = $('#select1U').val();
    var adults = $('#text-inputU').val();
    var kids = $('#text-input1U').val();
    var checkIn = $('#text-input2U').val();
    var checkOut = $('#text-input3U').val();
    var extraBed=$('#checkbox1U').val();
    var checkIn1 = new Date(checkIn);
    var checkOut1 = new Date(checkOut);
    var Difference_In_Time = checkOut1.getTime() - checkIn1.getTime();
    var night = Difference_In_Time / (1000 * 3600 * 24);
    var data = {
        guest: guest,
        roomType: roomType,
        adults: adults,
        kids: kids,
        checkIn: checkIn,
        checkOut: checkOut,
        extraBed:extraBed,
        night:night,
        reservationId: reservationId
    };
    $.ajax({
        url: 'updateReservation',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Reservation has been successfully updated');
                window.location.href = '/hotelMonalit/getReservationList';
            } else {
                alert('Problem! Reservation has not been successfully updated!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function updateReservation1(reservationId) {
    var bookingStatus = $('#select1U').val();
    var data = {
        bookingStatus:bookingStatus,
        reservationId: reservationId
    };
    $.ajax({
        url: 'updateReservation1',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Reservation has been successfully updated');
                window.location.href = '/hotelMonalit/getReservationList';
            } else {
                alert('Problem! Reservation has not been successfully updated!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function updateReservation2(reservationId) {
    var paymentStatus = $('#select2U').val();
    var data = {
        paymentStatus:paymentStatus,
        reservationId: reservationId
    };
    $.ajax({
        url: 'updateReservation2',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Reservation has been successfully updated');
                window.location.href = '/hotelMonalit/getReservationList';
            } else {
                alert('Problem! Reservation has not been successfully updated!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}




function updateReservationRoom(reservationRoomId) {
    var room = $('#selectU').val();
    var data = {
        room: room,
        reservationRoomId: reservationRoomId
    };
    $.ajax({
        url: 'updateReservationRoom',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Room has been successfully updated');
                window.location.href = '/hotelMonalit/getReservationList';
            } else {
                alert('Problem! Room has not been successfully updated!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function addReservationRoom(reservationId) {
    var room = $('#selectU').val();
    var data = {
        room: room,
        reservationId: reservationId
    };
    $.ajax({
        url: 'addReservationRoom',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Room has been successfully added');
                window.location.href = '/hotelMonalit/getReservationList';
            } else {
                alert('Problem! Room has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function addServiceReservation(reservationId) {
    var serviceId = $('#select3U').val();
    var data = {
        serviceId: serviceId,
        reservationId: reservationId
    };
    $.ajax({
        url: 'addServiceReservation',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Service has been successfully added');
                window.location.href = '/hotelMonalit/getReservationList';
            } else {
                alert('Problem! Service has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function deleteReservation(reservationId, name) {
    var isDelete = confirm("Are you sure to delete " + name + " ?");
    if (isDelete) {
        $.ajax({
            url: 'deleteReservation',
            type: 'POST',
            data: 'reservationId=' + reservationId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Reservation has been successfully deleted.');
                    window.location.href = '/hotelMonalit/getReservationList';
                } else {
                    alert('Reservation has not been successfully deleted!');
                }
            }
        });
    }
}

function advancedSearchReservationData() {
    var roomTypeCombo = $('#advRoomTypeCmbId').val();
    var paymentStatusCombo = $('#advPaymentStatusCmbId').val();
    var bookingStatusCombo = $('#advBookingStatusCmbId').val();
    var guestCombo = $('#advGuestCmbId').val();
    var beginDate = $('#advBeginCheckInDateId').val();
    var endDate = $('#advBeginCheckOutDateId').val();
    var data = {
        roomTypeCombo: roomTypeCombo,
        paymentStatusCombo: paymentStatusCombo,
        bookingStatusCombo: bookingStatusCombo,
        guestCombo: guestCombo,
        beginDate: beginDate,
        endDate: endDate
    };
    $.ajax({
        url: 'advancedSearchReservationData',
        type: 'GET',
        data: data,
        dataType: 'html',
        success: function (response) {
            if (response = 'success') {
                // alert('Reservation has been successfully deleted.');
                //window.location.href='/hotel/rs?action=getReservationList';
            } else {
                alert('Reservation has not been successfully deleted!');
            }
        }
    });
}

$(function () {
  $('#reservationSubmitIda').click(function () {
        addReservation();
    });
    $('#reservationRoomSubmitIdU').click(function () {
        updateReservationRoom();
    });
    $('#advSearchReservationBtnId').click(function () {
        advancedSearchReservationData();
    });

});