document.addEventListener('DOMContentLoaded', function() {
    populateMonthsAndYears();
    loadEventsFromLocalStorage();

    // Obter data atual
    var today = new Date();
    var currentYear = today.getFullYear();
    var currentMonth = today.getMonth();

    // Definir seleção de mês e ano para a data atual
    document.getElementById('monthSelect').value = currentMonth;
    document.getElementById('yearSelect').value = currentYear;

    // Atualizar o calendário para a data atual
    updateCalendar(currentYear, currentMonth);

    document.getElementById('monthSelect').addEventListener('change', function() {
        updateCalendar(document.getElementById('yearSelect').value, this.value);
    });

    document.getElementById('yearSelect').addEventListener('change', function() {
        updateCalendar(this.value, document.getElementById('monthSelect').value);
    });

    document.getElementById('calendar').addEventListener('click', function(event) {
        if (event.target.classList.contains('day') && !event.target.classList.contains('disabled')) {
            selectedYear = document.getElementById('yearSelect').value;
            selectedMonth = document.getElementById('monthSelect').value;
            selectedDay = event.target.textContent.trim();
            document.getElementById('eventModalLabel').textContent = `Adicionar Evento em ${selectedDay}/${parseInt(selectedMonth) + 1}/${selectedYear}`;
            document.getElementById('eventModal').style.display = 'block';
        }
    });

    document.querySelector('.close').addEventListener('click', function() {
        document.getElementById('eventModal').style.display = 'none';
    });
});

var selectedYear, selectedMonth, selectedDay;

function populateMonthsAndYears() {
    var months = ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'];
    var years = [];
    for (var i = new Date().getFullYear(); i >= 1900; i--) {
        years.push(i);
    }

    document.getElementById('monthSelect').innerHTML = months.map(function(month, index) {
        return `<option value="${index}">${month}</option>`;
    }).join('');

    document.getElementById('yearSelect').innerHTML = years.map(function(year) {
        return `<option value="${year}">${year}</option>`;
    }).join('');
}

function updateCalendar(year, month) {
    var events = JSON.parse(localStorage.getItem('events')) || {};
    var daysInMonth = new Date(year, parseInt(month) + 1, 0).getDate();
    var firstDayOfWeek = new Date(year, month, 1).getDay();
    var today = new Date();
    var calendarDiv = document.getElementById('calendar');

    calendarDiv.innerHTML = ''; // Limpa o calendário

    // Adiciona os dias da semana no topo
    var weekDays = ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'];
    var weekDaysRow = weekDays.map(day => `<div class="col week-day">${day}</div>`).join('');
    calendarDiv.innerHTML += weekDaysRow;

    // Gera os dias vazios antes do primeiro dia do mês
    for (var i = 0; i < firstDayOfWeek; i++) {
        calendarDiv.innerHTML += '<div class="col day empty"></div>';
    }

    // Preenche o calendário com os dias do mês
    for (var i = 1; i <= daysInMonth; i++) {
        var dayElement = document.createElement('div');
        dayElement.className = "col day";
        dayElement.textContent = i;

        var currentDay = new Date(year, month, i);

        // Destaca o dia atual
        if (isSameDay(currentDay, today)) {
            dayElement.style.backgroundColor = '#ffc107'; // Cor amarela para o dia atual
        }

        // Desabilita dias anteriores ao dia atual
        if (currentDay < today && !isSameDay(currentDay, today)) {
            dayElement.classList.add('disabled');
        }

        // Destaca finais de semana
        if (currentDay.getDay() === 0 || currentDay.getDay() === 6) {
            dayElement.style.fontWeight = 'bold';
        }

        // Adiciona eventos
        var eventKey = `${year}-${month}-${i}`;
        if (events[eventKey]) {
            dayElement.innerHTML = `<span>${i}</span>${events[eventKey].map(event => `<div>${event.time} - ${event.title}: ${event.description} <button class="btn btn-danger btn-sm remove-event" data-date="${eventKey}" data-title="${event.title}" data-time="${event.time}">X</button></div>`).join('')}`;
        }

        calendarDiv.appendChild(dayElement);
    }

    updateSelectedDayInfo();
}

function addEvent() {
    var eventTitle = document.getElementById('eventTitle').value.trim();
    var eventTime = document.getElementById('eventTime').value.trim();
    var eventDescription = document.getElementById('eventDescription').value.trim();
    var eventDate = `${selectedYear}-${selectedMonth + 1}-${selectedDay}`; // Ajuste o formato da data

    if (!eventTitle || !eventTime || !eventDescription) return;

    var event = {
        title: eventTitle,
        time: eventTime,
        description: eventDescription,
        date: eventDate
    };

    fetch('/admin/events', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(event)
    })
        .then(response => response.json())
        .then(data => {
            alert('Evento adicionado!');
            updateCalendar(selectedYear, selectedMonth);
            updateSelectedDayInfo();
            document.getElementById('eventModal').style.display = 'none';
        })
        .catch(error => console.error('Erro ao adicionar evento:', error));
}

document.body.addEventListener('click', function(event) {
    if (event.target.classList.contains('remove-event')) {
        var eventId = event.target.getAttribute('data-id');
        fetch(`/admin/events/${eventId}`, {
            method: 'DELETE'
        })
            .then(() => {
                alert('Evento removido!');
                updateCalendar(selectedYear, selectedMonth);
                updateSelectedDayInfo();
            })
            .catch(error => console.error('Erro ao remover evento:', error));
    }
});

function loadEventsFromLocalStorage() {
    var selectedDate = `${selectedYear}-${selectedMonth + 1}-${selectedDay}`;
    fetch(`/admin/events/${selectedDate}`)
        .then(response => response.json())
        .then(events => {
            events.forEach(event => addEventToCalendar(event.date, event));
        })
        .catch(error => console.error('Erro ao carregar eventos:', error));
}


function loadEventsFromLocalStorage() {
    var events = JSON.parse(localStorage.getItem('events'));
    if (!events) return;

    Object.keys(events).forEach(function(date) {
        var [year, month, day] = date.split('-');
        addEventToCalendar(year, month, day, events[date]);
    });
}

function addEventToCalendar(date, event) {
    var [year, month, day] = date.split('-');
    var dayElement = document.querySelector(`.day[data-date="${year}-${month}-${day}"]`);
    if (dayElement) {
        var eventElement = document.createElement('div');
        eventElement.innerHTML = `${event.time} - ${event.title}: ${event.description} <button class="btn btn-danger btn-sm remove-event" data-id="${event.id}">X</button>`;
        dayElement.appendChild(eventElement);
    }
}



function isSameDay(date1, date2) {
    return date1.getFullYear() === date2.getFullYear() &&
        date1.getMonth() === date2.getMonth() &&
        date1.getDate() === date2.getDate();
}

function updateSelectedDayInfo() {
    var selectedDate = `${selectedYear}-${selectedMonth}-${selectedDay}`;
    var events = JSON.parse(localStorage.getItem('events')) || {};
    var eventDetails = document.getElementById('eventDetails');

    eventDetails.innerHTML = `<h3>Eventos em ${selectedDay}/${parseInt(selectedMonth) + 1}/${selectedYear}</h3>`;
    if (events[selectedDate]) {
        eventDetails.innerHTML += events[selectedDate].map(event => `<div><strong>${event.time} - ${event.title}</strong>: ${event.description}</div>`).join('');
    } else {
        eventDetails.innerHTML += `<p>Nenhum evento para este dia.</p>`;
    }
}
