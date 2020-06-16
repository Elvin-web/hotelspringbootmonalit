function addPayment() {
    var reservationId = $('#text-input4').val();
    var paymentId = $('#text-input9').val();
    var addedDate = $('#text-input1').val();
    var paymentMethod = $('#select1').val();
    var amount = $('#text-input3').val();
    var data = {
        reservationId: reservationId,
        paymentId:paymentId,
        addedDate: addedDate,
        paymentMethod: paymentMethod,
        amount: amount
    };
    $.ajax({
        url: 'addPayment',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Payment has been successfully added');
                window.location.href='/hotelMonalit/getReservationList';
            } else {
                alert('Problem! Payment has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function updatePayment(paymentId) {
    var reservationId = $('#text-input4').val();
    var addedDate = $('#text-input1').val();
    var paymentMethod = $('#select1').val();
    var amount = $('#text-input3').val();
    var data = {
        reservationId: reservationId,
        addedDate: addedDate,
        paymentMethod: paymentMethod,
        amount: amount,
        paymentId: paymentId
    };
    $.ajax({
        url: 'updatePayment',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Payment has been successfully updated');
                window.location.href='/hotelMonalit/getReservationList';
            } else {
                alert('Problem! Payment has not been successfully updated!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

$(function () {
    // $("[data-toggle=tooltip]").tooltip();
    $('#closeId').click(function () {
        $('#successMessage').dialog("close");
    });
    $('#closeIdE').click(function () {
        $('#errorMessage').dialog("close");
    });
    $('#paymentSubmitId').click(function () {
        addPayment();
    });
    /*$('#paymentSubmitIdU').click(function () {
        updatePayment(paymentId);
    });*/

});