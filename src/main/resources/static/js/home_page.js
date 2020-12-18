$(document).ready(function () {

    $('#getCourses').click(function () {
        var url;
        if(window.localStorage.getItem("userType") === "M_")
            url = "http://localhost:8080/courses/"
        else
            url = "http://localhost:8080/courses/" + window.localStorage.getItem("userId")
        console.log(url)
        $.ajax({
            type: 'GET',
            url: url,
            success: function (coursesData) {
                for (var index in coursesData) {
                    var courseData = coursesData[parseInt(index)]
                    console.log(courseData)
                    $('#list').append(`
                        <div class="col-sm-4 p-4">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">${courseData.title}</h5>
                                    <p class="card-text">${courseData.startDate}</p>
                                    <p class="card-text">${courseData.endDate}</p>           
                                    <a href="http://localhost:8080/course-page/${courseData.id}" class="btn btn-primary">Course page</a>
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
    });


})