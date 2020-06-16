var globAmenitiesId = 0;
var globBtnId = " ";
$(function () {
    // $("[data-toggle=tooltip]").tooltip();
    $('#closeId').click(function () {
        $('#successMessage').dialog("close");
    });
    $('#closeIdE').click(function () {
        $('#errorMessage').dialog("close");
    });

    $('#editAmenitiesDialogId').dialog({
        title: 'Information Message',
        height: 200,
        width: 550,
        autoOpen: false,
        modal: true
    });
    $('#searchBtnId').click(function () {
        var keyword = $('#keywordId').val();

        switch (globBtnId) {
            case 'amenitiesBtnId':
                searchAmenitiesData(keyword);
                break;
            default:
                alert('Please,select menu!');
        }
    });
});

function searchAmenitiesData(keyword) {
    $.ajax({
        url: 'cs?action=searchAmenitiesData',
        type: 'GET',
        data: 'keyword=' + keyword,
        dataType: 'html',
        success: function (response) {
        //    $('#amenitiesDivId').html(response);
        }
    });
}


/*function getCityListByCountryId(countryId) {
    $.ajax({
        url: 'getCityListByCountryId',
        type: 'GET',
        dataType: 'html',
        data: 'countryId' + countryId,
        success(response) {
            $('#advCityCmbId').html(response);
        }
    });
}

function advancedSearchGuestData() {
    var countryCombo = $('#advCountryCmbId').val();
    var cityCombo = $('#advCityCmbId').val();
    var passportCombo = $('#advIDCmbId').val();
    var name = $('#advNameId').val();
    var surname = $('#advSurnameId').val();
    var phone = $('#advPhoneId').val();
    var email = $('#advEmailId').val();
    var ID = $('#advIDId').val();
    var beginDate = $('#advBeginDateId').val();
    var endDate = $('#advEndDateId').val();
    var data = {
        countryCombo: countryCombo,
        cityCombo: cityCombo,
        passportCombo: passportCombo,
        name: name,
        surname: surname,
        phone: phone,
        email: email,
        ID: ID,
        beginDate: beginDate,
        endDate: endDate
    };
    $.ajax({
        url: 'advancedSearchGuestData',
        type: 'GET',
        data: data,
        dataType: 'html',
        success(response) {
            $('#guestDivId').html(response);
        }
    });
}*/

$(function () {
    $('#searchBtnId').click(function () {
        var keyword = $('#keywordId').val();
        switch (globBtnId) {
            case'bookingId':
                searchReservationData(keyword);
                break;
            case'guestId':
                searchGuestData(keyword);
                break;
        }
    });
});
function searchGuestData(keyword) {
    $.ajax({
            url: 'cs?action=searchGuestData',
            type:'GET',
            data:'keyword'+keyword,
            dataType:'html',
            success:function () {
                $('.main-content').html(response);

            }
        }
    )
}