        var chartData = document.getElementById("piechart").getAttribute("data-values").split("|");
        var chartColors = document.getElementById("piechart").getAttribute("color-values").split("|");
        var chartLabels = document.getElementById("piechart").getAttribute("label-values").split("|");

		var config = {
			type: 'bar',
			data: {
				datasets: [{
					data: chartData,
					backgroundColor: chartColors,
					label: 'Sold per Category'
				}],
				labels: chartLabels
			},
			options: {
				responsive: true
			}
		};

		window.onload = function() {
			var ctx = document.getElementById('chart-area').getContext('2d');
			window.myPie = new Chart(ctx, config);
		};


