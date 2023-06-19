const upcoming = document.getElementById("upcoming");
const today = document.getElementById("today");
const AllTasks = document.getElementById("all-tasks");
const completedTasks = document.getElementById("completed-tasks");
const addTask = document.getElementById("add_task");

var content = document.getElementById("dynamic_content");

upcoming.addEventListener("click", function(event) {
    let url = '/upcomingTasks';
    resetButtons(event);
    contentAjaxGetTasks(url);
});
today.addEventListener("click", function(event) {
    let url = '/todayTasks';
    resetButtons(event);
    contentAjaxGetTasks(url);
});
AllTasks.addEventListener("click", function(event) {
   let url = '/allTasks';
   resetButtons(event);
   contentAjaxGetTasks(url);
});
completedTasks.addEventListener("click", function(event) {
   let url = '/completedTasks';
   resetButtons(event);
   contentAjaxGetTasks(url);
});
addTask.addEventListener("click", function(event) {
    resetButtons(event);
    contentAddTask();
});

$('#dynamic_content').on('click', '.task-row', function(event) {
    let rowId = $(this).attr('id');
    let taskId = rowId.substring(rowId.indexOf('-') + 1);

    if (!$(event.target).is(':checkbox')) {
        resetButtons(null);
        contentAjaxGetTask(taskId);
    } else {
        var formData = {
            is_completed: $(this).find('input[type="checkbox"]').prop('checked')
        };
        let row = $(this).closest('tr');
        ajaxPutTaskCompleted(taskId, formData, row);
    }
});

function resetButtons(event) {
    var buttons = document.querySelectorAll(".menu-button");

    buttons.forEach(function(button) {
        button.classList.remove("active");
    })
    if(event !== null) {
        let button = event.target;
        button.classList.add('active');
    }
}

function contentAddTask() {
    createForm();
    document.getElementById('form-buttons').innerHTML = '<input type="submit" value="ADD TASK">';

    document.getElementById("task-form").addEventListener("submit", function(e) {
        e.preventDefault();

        var formData = {
            title: $('#title').val(),
            description: $('#description').val(),
            due_date: $('#date').val(),
            due_time: $('#time').val(),
            is_completed: $('#completed').prop('checked')
        };
        contentAjaxPostTask(formData);
    });
}

function contentEditTask(taskId) {
    document.getElementById('title').readOnly = false;
    document.getElementById('description').readOnly = false;
    document.getElementById('date').readOnly = false;
    document.getElementById('time').readOnly = false;
    document.getElementById('completed').disabled = false;

    document.getElementById('form-buttons').innerHTML = `
        <input type="submit" value="ACCEPT">
    `;

    document.getElementById("task-form").addEventListener("submit", function(e) {
        e.preventDefault();

        var formData = {
            title: $('#title').val(),
            description: $('#description').val(),
            due_date: $('#date').val(),
            due_time: $('#time').val(),
            is_completed: $('#completed').prop('checked')
        };
        contentAjaxPutTask(taskId, formData);
    });
}

function ajaxPutTaskCompleted(taskId, formData, row) {
    $.ajax({
        url: '/task/complete/'+taskId,
        method: 'PUT',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function(response) {
            row.remove();
            console.log(response.message);
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

function contentAjaxGetTask(taskId) {
    $.ajax({
        url: '/task/'+taskId,
        method: 'GET',
        success: function(response) {
            createForm();
            document.getElementById('title').value = response.title;
            document.getElementById('title').readOnly = true;
            document.getElementById('description').value = response.description;
            document.getElementById('description').readOnly = true;
            document.getElementById('date').value = response.due_date;
            document.getElementById('date').readOnly = true;
            document.getElementById('time').value = response.due_time;
            document.getElementById('time').readOnly = true;
            document.getElementById('completed').checked = response.is_completed;
            document.getElementById('completed').disabled = true;

            document.getElementById('form-buttons').innerHTML = `
                <input type="button" id="edit-task" value="EDIT TASK">
                <input type="button" id="delete-task" value="DELETE TASK">
            `;

            document.getElementById('delete-task').addEventListener('click', function() {
                contentAjaxDeleteTask(taskId);
            });
            document.getElementById('edit-task').addEventListener("click", function(event) {
                event.stopPropagation();
                contentEditTask(taskId);
            });
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

function contentAjaxPutTask(taskId, formData) {
    $.ajax({
        url: '/task/'+taskId,
        method: 'PUT',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function(response) {
            content.innerHTML = '';
            var text = $('<p class="message">').text(response.message);
            text.appendTo(content);
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

function contentAjaxDeleteTask(taskId) {
    $.ajax({
        url: '/task/'+taskId,
        method: 'DELETE',
        success: function(response) {
            content.innerHTML = '';
            var text = $('<p class="message">').text(response.message);
            text.appendTo(content);
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

function contentAjaxPostTask(formData) {
    $.ajax({
        url: '/task',
        type: 'POST',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function(response) {
            resetButtons(null);
            content.innerHTML = '';
            var text = $('<p class="message">').text(response.message);
            text.appendTo(content);
        },
        error: function(error) {
            console.log(error);
        }
    });
}

function contentAjaxGetTasks(url) {
    $.ajax({
        url: url,
        method: 'GET',
        success: function(response) {
            var table = $('<table id="taskTable" class="task-table">');

            response.forEach(function(task) {
                var row = $('<tr id="taskId-'+task.id+'" class="task-row">');

                var timeValue =  task.due_time;
                if(timeValue != null) {
                    timeValue = timeValue.substring(0, 5);
                }

                row.append($('<td class="title">').text(task.title));
                row.append($('<td class="due_date">').text(task.due_date));
                row.append($('<td class="due_time">').text(timeValue));

                var checkboxCell = $('<td class="is_completed">');
                var checkbox = $('<input type="checkbox">').prop('checked', task.is_completed);
                checkboxCell.append(checkbox);

                row.append(checkboxCell);
                table.append(row);

            content.innerHTML = '';
            table.appendTo(content);
            });
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

function createForm() {
    const contentHTML = `
    <form id="task-form" class="add-form">
        <div>
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" maxlength="255" required>
        </div>
        <div>
            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="4"></textarea>
        </div>
        <div>
            <label for="date">Date:</label>
            <input type="date" id="date" name="date">
        </div>
        <div>
            <label for="time">Time:</label>
            <input type="time" id="time" name="time">
        </div>
        <div>
            <label for="completed">Completed:</label>
            <input type="checkbox" id="completed" name="completed">
        </div>
        <div id="form-buttons"></div>
    </form>
    `;
    content.innerHTML = contentHTML;
}
