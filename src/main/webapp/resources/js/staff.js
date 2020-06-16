function addStaff() {
    var name = $('#text-input').val();
    var surname = $('#text-input1').val();
    var middleName = $('#text-input2').val();
    var address = $('#text-input3').val();
    var dob = $('#text-input4').val();
    var phone = $('#text-input5').val();
    var jobStart = $('#text-input6').val();
    var jobEnd = $('#text-input7').val();
    var salary = $('#text-input8').val();
    var position = $('#select').val();
    var image = $('#file-input').val();
    var male = $('#inline-radio1').val();
    var female = $('#inline-radio2').val();
    var data = {
        name: name,
        surname: surname,
        middleName: middleName,
        address: address,
        dob: dob,
        phone: phone,
        jobStart: jobStart,
        jobEnd: jobEnd,
        salary: salary,
        position: position,
        image: image,
        male: male,
        female: female
    };
    $.ajax({
        url: 'ss?action=addStaff1',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Staff has been successfully added!');
                window.location.href='/hotel/ss?action=getStaffList';
            } else {
                alert('Problem! Staff has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function updateStaff(staffId) {
    var name = $('#text-inputU').val();
    var surname = $('#text-input1U').val();
    var middleName = $('#text-input2U').val();
    var address = $('#text-input3U').val();
    var dob = $('#text-input4U').val();
    var phone = $('#text-input5U').val();
    var jobStart = $('#text-input6U').val();
    var jobEnd = $('#text-input7U').val();
    var salary = $('#text-input8U').val();
    var position = $('#selectU').val();
    var image = $('#file-inputU').val();
    var male = $('#inline-radio1U').val();
    var female = $('#inline-radio2U').val();
    var data = {
        name: name,
        surname: surname,
        middleName: middleName,
        address: address,
        dob: dob,
        phone: phone,
        jobStart: jobStart,
        jobEnd: jobEnd,
        salary: salary,
        position: position,
        image: image,
        male: male,
        female: female,
        staffId:staffId
    };
    $.ajax({
        url: 'ss?action=updateStaff',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Staff has been successfully update!');
                window.location.href='/hotel/ss?action=getStaffList';
            } else {
                alert('Problem! Staff has not been successfully update!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function deleteStaff(staffId, name) {
    var isDelete = confirm("Are you sure to delete " + name + " ?");
    if (isDelete) {
        $.ajax({
            url: 'deleteStaff',
            type: 'POST',
            data: 'staffId=' + staffId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Staff has been successfully deleted!');
                    window.location.href='/hotelMonalit/getStaffList';
                } else {
                    alert('Problem! Staff has not been successfully deleted!');
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
    $('#staffSubmitId').click(function () {
        addStaff();
    });
    $('#staffSubmitIdU').click(function () {
        updateStaff();
    });

});