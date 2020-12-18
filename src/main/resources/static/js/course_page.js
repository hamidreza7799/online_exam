$(document).ready(function () {
    var getExamsOfCourse = "http://localhost:8080/exams/"
    var courseId;
    var cookieArr = document.cookie.split(";");
    for(var i = 0; i < cookieArr.length; i++) {
        var cookiePair = cookieArr[i].split("=");
        if("courseId" === cookiePair[0].trim()) {
            courseId = cookiePair[1].toString()
        }
    }
    getExamsOfCourse += courseId.toString()
    $.ajax({
        type:'GET',
        url: getExamsOfCourse,
        success: function (examsData) {
            for (var index in examsData) {
                var examData = examsData[parseInt(index)]
                console.log(examData)
                $('#examList').append(`
                        <div class="col-sm-4 p-4">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">${examData.title}</h5>
                                    <p class="card-text">${examData.startDateTime}</p>
                                    <p class="card-text">${examData.endDateTime}</p>           
                                    <p class="card-text">${examData.time}</p>           
                                    <a href="http://localhost:8080/course-page/${examData.id}" class="btn btn-primary">Course page</a>
                                </div>
                            </div>
                        </div>
                    `)
            }
        },
        error: function (data) {
            console.log("error")
            console.log(data);
        }
    })


    $('#start-date').datepicker({
        uiLibrary: 'bootstrap4',
        format: 'yyyy-mm-dd',
    });
    $('#start-time').timepicker({
        uiLibrary: 'bootstrap4',
    });
    $('#end-date').datepicker({
        uiLibrary: 'bootstrap4',
        format: 'yyyy-mm-dd',
    });
    $('#end-time').timepicker({
        uiLibrary: 'bootstrap4',
    });

    $('#creatExamButton').click(function () {
        $('#examList').addClass('d-none');
        $('#createExamForm').removeClass('d-none')
    })

    $('#submitCreateExam').click(function () {
        console.log("hello")
        console.log(getExamsOfCourse + "/new-exam")
        var title = $('#title').val()
        var description = $('#description').val()
        var examLength = $('#exam-length').val()
        var startDateTime = $('#start-date').val() + ' ' + $('#start-time').val()
        var endDateTime = $('#end-date').val() + ' ' + $('#end-time').val()
        $.ajax({
            type: 'POST',
            url: getExamsOfCourse + "/new-exam",
            contentType:"application/json",
            data:JSON.stringify({
              title:title,
              description:description,
              time:examLength,
              startDateTime:startDateTime,
              endDateTime:endDateTime
            }),
            success:function (newExamId) {
                $('#examList').removeClass('d-none');
                $('#createExamForm').addClass('d-none')
                $('#examList').append(`
                        <div class="col-sm-4 p-4">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">${title}</h5>
                                    <p class="card-text">${startDateTime}</p>
                                    <p class="card-text">${endDateTime}</p>           
                                    <p class="card-text">${examLength}</p>           
                                    <a href="http://localhost:8080/course-page/${newExamId}" class="btn btn-primary">Course page</a>
                                </div>
                            </div>
                        </div>
                    `)
            },
            error:function (errorMessage) {
                console.log(errorMessage)
            }
        })
    })

})