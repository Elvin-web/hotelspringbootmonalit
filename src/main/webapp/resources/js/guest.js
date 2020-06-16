function getCityListByCountryId(countryId) {
    $.ajax({
        url: 'getCityListByCountryId',
        type: 'GET',
        data: 'countryId=' + countryId,
        dataType: 'html',
        success: function (response) {
            $('#select1').html(response);
        }
    });
}

/*function addGuest() {
    var name = $('#text-input').val();
    var surname = $('#text-input1').val();
    var male = $('#inline-radio1').val();
    var female = $('#inline-radio2').val();
    var dob = $('#text-input4').val();
    var phone = $('#text-input2').val();
    var email = $('#email-input').val();
    var country = $('#select').val();
    var city = $('#select1').val();
    var passport = $('#select2').val();
    var ID = $('#text-input3').val();
    var data = {
        name: name,
        surname: surname,
        dob: dob,
        phone: phone,
        email: email,
        country: country,
        city: city,
        passport: passport,
        ID: ID,
        male: male,
        female: female
    };
    $.ajax({
        url: 'gs?action=addGuest1',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Guest has been successfully added');
                window.location.href='/hotel/gs?action=getGuestList1';
            } else {
                alert('Problem! Guest has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}*/
function updateGuest(guestId) {
    var name = $('#text-inputU').val();
    var surname = $('#text-input1U').val();
    var male = $('#inline-radio1U').val();
    var female = $('#inline-radio2U').val();
    var dob = $('#text-input4U').val();
    var phone = $('#text-input2U').val();
    var email = $('#email-inputU').val();
    var country = $('#selectU').val();
    var city = $('#select1U').val();
    var passport = $('#select2U').val();
    var ID = $('#text-input3U').val();
    var data = {
        name: name,
        surname: surname,
        dob: dob,
        phone: phone,
        email: email,
        country: country,
        city: city,
        passport: passport,
        ID: ID,
        male: male,
        female: female,
        guestId: guestId
    };
    $.ajax({
        url: 'gs?action=updateGuest',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Guest has been successfully updated');
                window.location.href='/hotel/gs?action=getGuestList1';
                //  $('#successMessage').load('views/successMessage.jsp').dialog('open');
                //window.url
                // window.location.href = '/WEB-INF/pages/guestList.jsp';
                //  window.location.replace("/WEB-INF/pages/guestList.jsp");
            } else {
                alert('Problem! Guest has not been successfully updated!');
                // $('#errorMessage').load('views/errorMessage.jsp').dialog('open');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function deleteGuest(guestId, name) {
    var isDelete = confirm("Are you sure to delete " + name + " ?");
    if (isDelete) {
        $.ajax({
            url: 'deleteGuest',
            type: 'POST',
            data: 'guestId=' + guestId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Guest has been successfully deleted!');
                    window.location.href='/hotelMonalit/getGuestList';
                } else {
                    alert('Problem! Guest has not been successfully deleted!');
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
  /*  $('#guestSubmitId').click(function () {
        addGuest();
    });*/
    $('#guestSubmitIdU').click(function () {
        updateGuest(guestId);
    });

});