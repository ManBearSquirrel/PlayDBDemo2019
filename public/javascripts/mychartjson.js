var data = document.getElementById("piechart").getAttribute("data-values").split(",");

var config = {
    type: 'pie',
    data: {
        datasets: [{
            data: data,
            backgroundColor: [
                'rgb(255, 0, 0)',
                'rgb(255, 0, 255)',
                'rgb(255, 255, 0)',
                'rgb(0, 255, 0)',
                'rgb(0, 0, 255)'
            ],
            label: 'Dataset 1'
        }],
        labels: [
            'Red Five',
            'Orange',
            'Yellow Brick Road',
            'Green',
            'Blue Sky on Mars'
        ]
    },
    options: {
        responsive: true
    }
};

window.onload = function() {
    var ctx = document.getElementById('chart-area').getContext('2d');
    new Chart(ctx, config);
};

