
$(document).ready(function () {
    var examId;
    var cookieArr = document.cookie.split(";");
    for(var i = 0; i < cookieArr.length; i++) {
        var cookiePair = cookieArr[i].split("=");
        if("examId" === cookiePair[0].trim()) {
            examId = cookiePair[1].toString()
        }
    }
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/questions/",
        success:function (data) {
            console.log(data)
            for (index in data){
                if(data[index].questionType === "LongAnswer"){
                    $("#question-bank").append(`
                        <div class="col-sm-12 p-4" id="card-question-${data[index].id}">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">${data[index].title}</h5>
                                    <p class="card-text text-justify">${data[index].questionText}</p>
                                    <hr>
                                    <p class="card-text text-justify">${data[index].answerText}</p>
                                    <button  id="add-to-exam-${data[index].id}" class="btn btn-primary">Add-To-Exam</button>
                                </div>
                            </div>
                        </div>
                    `)
                }
                else if(data[index].questionType === "MultipleChoice"){
                    $("#question-bank").append(`
                        <div class="col-sm-12 p-4" id="card-question-${data[index].id}">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">${data[index].title}</h5>
                                    <p class="card-text text-justify">${data[index].questionText}</p>
                                    <hr>
                                    <div id="question-choices-${data[index].id}">
                                    </div>
                                    <button  id="add-to-exam-${data[index].id}" class="btn btn-primary">Add-To-Exam</button>
                                </div>
                            </div>
                        </div>
                    `)
                    for(var choiceIndex in data[index].questionChoice){
                        if(data[index].questionChoice[choiceIndex].isAnswer){
                            $(`question-choices-${data[index].id}`).append(`
                               <input class="form-check-input" type="checkbox" value="" id="question-${data[index].id}-${choiceIndex}"   checked/>
                               <label class="form-check-label" for="question-${data[index].id}-${choiceIndex}">Default checkbox</label>
                               <br>
                            `)
                        }
                        else {
                            $(`question-choices-${data[index].id}`).append(`
                               <input class="form-check-input" type="checkbox" value="" id="question-${data[index].id}-${choiceIndex}"/>
                               <label class="form-check-label" for="question-${data[index].id}-${choiceIndex}">Default checkbox</label>
                               <br>
                            `)
                        }
                    }
                }

                $(`#add-to-exam-${data[index].id}`).click(function () {
                    var grad = prompt("Please enter the grad of exam:", 2.5);
                    var description = prompt("Please enter the description of exam:", "Harry Potter");
                    var questionType = prompt("Please enter the description of exam:", "LongAnswer");
                    var questionId = this.id.split("-")[3]
                    var data = JSON.stringify({
                        "grade":grad,
                        "description":description,
                        "questionType":questionType,
                        "questionId":questionId
                    })
                    console.log(data)
                    console.log(questionId)
                    $.ajax({
                        type:"POST",
                        url:"http://localhost:8080/exam-question/" + examId,
                        contentType:"application/json",
                        data:data,
                        success:function(examQuestion){
                            $("#exam-questions").append(`
                            <div class="col-sm-12 p-4" id="card-exam-question-${examQuestion.id}">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title">${examQuestion.questionId}</h5>
                                        <p class="card-text text-justify">${examQuestion.description}</p>
                                        <p class="card-text text-justify">${examQuestion.grade}</p>
                                        <button  id="remove-exam-question-${examQuestion.id}" class="btn btn-danger">Remove-exam-question</button>
                                    </div>
                                </div>
                            </div>
                            `)
                            $(`#remove-exam-question-${examQuestion.id}`).click(function () {
                                var examQuestionId = this.id.split("-")[3]
                                console.log(examQuestionId)
                                $.ajax({
                                    type:"DELETE",
                                    url:"http://localhost:8080/exam-question/" + examQuestionId,
                                    success:function(){
                                        $(`#card-exam-question-${examQuestionId}`).remove()
                                        alert("Delete question")
                                    },
                                    error:function () {
                                        alert("error")
                                    }
                                })
                            })
                        },
                        error:function () {
                            alert("error")
                        }
                    })
                })
            }
        }
    })

    // $.ajax({
    //     type:"GET",
    //     url:"http://localhost:8080/questions/",
    //     success:function (data) {
    //         console.log(data)
    //         for (index in data){
    //             if(data[index].questionType === "LongAnswer"){
    //                 $("#question-bank").append(`
    //                     <div class="col-sm-4 p-4" id="card-question-${data[index].id}">
    //                         <div class="card">
    //                             <div class="card-body">
    //                                 <h5 class="card-title">${data[index].title}</h5>
    //                                 <p class="card-text text-justify">${data[index].questionText}</p>
    //                                 <hr>
    //                                 <p class="card-text text-justify">${data[index].answerText}</p>
    //                                 <button  id="remove-question-${data[index].id}" class="btn btn-danger">Remove question</button>
    //                             </div>
    //                         </div>
    //                     </div>
    //                 `)
    //             }
    //             else if(data[index].questionType === "MultipleChoice"){
    //                 $("#question-bank").append(`
    //                     <div class="col-sm-4 p-4" id="card-question-${data[index].id}">
    //                         <div class="card">
    //                             <div class="card-body">
    //                                 <h5 class="card-title">${data[index].title}</h5>
    //                                 <p class="card-text text-justify">${data[index].questionText}</p>
    //                                 <hr>
    //                                 <div id="question-choices-${data[index].id}">
    //                                 </div>
    //                                 <button  id="add-to-exam-${data[index].id}" class="btn btn-primary">Add-To-Exam</button>
    //                             </div>
    //                         </div>
    //                     </div>
    //                 `)
    //                 for(var choiceIndex in data[index].questionChoice){
    //                     if(data[index].questionChoice[choiceIndex].isAnswer){
    //                         $(`question-choices-${data[index].id}`).append(`
    //                            <input class="form-check-input" type="checkbox" value="" id="question-${data[index].id}-${choiceIndex}"   checked/>
    //                            <label class="form-check-label" for="question-${data[index].id}-${choiceIndex}">Default checkbox</label>
    //                            <br>
    //                         `)
    //                     }
    //                     else {
    //                         $(`question-choices-${data[index].id}`).append(`
    //                            <input class="form-check-input" type="checkbox" value="" id="question-${data[index].id}-${choiceIndex}"/>
    //                            <label class="form-check-label" for="question-${data[index].id}-${choiceIndex}">Default checkbox</label>
    //                            <br>
    //                         `)
    //                     }
    //                 }
    //             }
    //
    //             $(`#add-to-exam-${data[index].id}`).click(function () {
    //                 var grad = prompt("Please enter the grad of exam:", 2.5);
    //                 var description = prompt("Please enter the description of exam:", "Harry Potter");
    //                 var questionType = data[index].questionType;
    //                 var questionId = this.id.split("-")[3]
    //                 var data = JSON.stringify({
    //                     "grade":grad,
    //                     "description":description,
    //                     "questionType":questionType,
    //                     "questionId":questionId
    //                 })
    //                 console.log(data)
    //                 console.log(questionId)
    //                 $.ajax({
    //                     type:"POST",
    //                     url:"http://localhost:8080/exam-question/" + examId,
    //                     data:data,
    //                     success:function(){
    //                         console.log("add question")
    //                     },
    //                     error:function () {
    //                         alert("error")
    //                     }
    //                 })
    //             })
    //         }
    //     }
    // })
})