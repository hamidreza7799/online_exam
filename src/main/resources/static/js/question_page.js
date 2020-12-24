$(document).ready(function () {
    var multipleChoice = false;
    var countOfChoices = 1;
    var longAnswer = true;

    $.ajax({
        type:"GET",
        url:"http://localhost:8080/questions/",
        success:function (data) {
            console.log(data)
            for (index in data){
                if(data[index].questionType === "LongAnswer"){
                    $("#list-of-questions").append(`
                        <div class="col-sm-4 p-4" id="card-question-${data[index].id}">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">${data[index].title}</h5>
                                    <p class="card-text text-justify">${data[index].questionText}</p>
                                    <hr>
                                    <p class="card-text text-justify">${data[index].answerText}</p>
                                    <button  id="remove-question-${data[index].id}" class="btn btn-danger">Remove question</button>
                                </div>
                            </div>
                        </div>
                    `)
                }
                else if(data[index].questionType === "MultipleChoice"){
                    $("#list-of-questions").append(`
                        <div class="col-sm-4 p-4" id="card-question-${data[index].id}">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">${data[index].title}</h5>
                                    <p class="card-text text-justify">${data[index].questionText}</p>
                                    <hr>
                                    <div id="question-choices-${data[index].id}">
                                    </div>
                                    <button  id="remove-question-${data[index].id}" class="btn btn-danger">Remove question</button>
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

                $(`#remove-question-${data[index].id}`).click(function () {
                    var questionId = this.id.split("-")[2]
                    console.log(questionId)
                    $.ajax({
                        type:"DELETE",
                        url:"http://localhost:8080/questions/" + questionId,
                        success:function(){
                            $(`#card-question-${questionId}`).remove()
                            alert("Delete question")
                        },
                        error:function () {
                            alert("error")
                        }
                    })
                })
            }
        }
    })

    $("#create-question-button").click(function () {
        console.log("hello")
        $("#list-of-questions").addClass('d-none')
        $("#create-question-form").removeClass('d-none')

        $("#long-answer").click(function () {
            $("#multiple-choice-div").remove()
            $("#multiple-choice").removeClass("active")
            $("#long-answer").addClass("active")
            if(!longAnswer)
                $("#answer-content").append(`
                        <div id="answer-text-div" class="form-group col-md-12">
                            <label for="answer-text">answer-text</label>
                            <input type="text" class="form-control" id="answer-text" >
                        </div>
            `)
            longAnswer = true;
            multipleChoice = false;
        })

        $("#multiple-choice").click(function () {
            $("#answer-text-div").remove()
            $("#multiple-choice").addClass("active")
            $("#long-answer").removeClass("active")
            if(!multipleChoice) {
                countOfChoices = 1;
                $("#answer-content").append(`
                     <div id="multiple-choice-div">
                        <p id="multiple-choice-title" class="title">Question choices</p>
                        <div id="question-choices">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="choice-answer-0">
                            <input type="text" class="form-control" id="choice-text-0">
                        </div>
                        </div>
                        <div id="multiple-choice-add-button" class="d-flex col-sm-12 justify-content-center align-items-center">
                            <button id="add-choice-button" type="button" class="btn btn-danger m-t-50">Add choice</button>
                        </div>
                     </div>
            `)
            }
            $("#add-choice-button").click(function () {
                $("#question-choices").append(`
               <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="choice-answer-${countOfChoices}">
                    <input type="text" class="form-control" id="choice-text-${countOfChoices}">
               </div>
        `)
                countOfChoices++;
            })
            multipleChoice = true;
            longAnswer = false;
        })

        $("#submit-create-question").click(function () {
            var data;
            var url = "http://localhost:8080/questions/";
            if(longAnswer){
                url = url + "long-answer"
                data = JSON.stringify({
                    "title":$("#question-title").val(),
                    "questionText":$("#question-text").val(),
                    "answerText":$("#answer-text").val()
                })
            }
            else {
                var questionChoices = [];
                for(var index = 0; index<countOfChoices; index++){
                    var newDate = {
                        "choiceText":$(`#choice-text-${index}`).val(),
                        "isAnswer":$(`#choice-answer-${index}`).is(":checked")
                    }
                    questionChoices.push(newDate)
                }
                url = url + "multiple-choice"
                data = JSON.stringify({
                    "title":$("#question-title").val(),
                    "questionText":$("#question-text").val(),
                    "questionChoice":questionChoices
                })
                console.log(data)
            }

            $.ajax({
                type:"POST",
                url: url,
                contentType:"application/json",
                data: data,
                success:function () {
                    alert("Question Create");
                },
                error:function () {
                    alert("error");
                }
            })
        })
    })



})