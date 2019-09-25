/////////////////////////////////////////////////
///////////////////Char 1////////////////////////
/////////////////////////////////////////////////

var dataset1 = [
  {"month": "January", "quantity": 4},
  {"month": "February", "quantity": 5},
  {"month": "March", "quantity": 5},
  {"month": "April", "quantity": 7},
  {"month": "May", "quantity": 15},
  {"month": "June", "quantity": 20},
  {"month": "July", "quantity": 35},
  {"month": "August", "quantity": 40},
  {"month": "October", "quantity": 10},
  {"month": "November", "quantity": 5},
  {"month": "December", "quantity": 10}
];

var svgWidth1 = 800, svgHeight1 = 800, radius1 = Math.min(svgWidth1, svgHeight1)/2;

var svg1 = d3.select('#char1')
.attr("width", svgWidth1)
.attr("height", svgHeight1);

var g1 = svg1.append("g")
.attr("transform", "translate(" + radius1 + "," + radius1 + ")");

var color1 = d3.scaleOrdinal(d3.schemeCategory10);

var pie1 = d3.pie().value(function(d){
  return d.quantity;
})

var path1 = d3.arc()
.outerRadius(radius1)
.innerRadius(0)

var arc1 = g1.selectAll("arc")
.data(pie1(dataset1))
.enter()
.append("g");

arc1.append("path")
.attr("d",path1)
.attr("fill", function(d) {
  return color1(d.data.quantity); });

var label1 = d3.arc()
.outerRadius(radius1)
.innerRadius(radius1-250);

arc1.append("text")
.attr("transform", function(d) {
  return "translate(" + label1.centroid(d) + ")";
})
.attr("text-anchor", "middle")
.text(function(d) { return d.data.month+": "+d.data.quantity; })
.style("font-size", 30)



/////////////////////////////////////////////////
///////////////////Char 2////////////////////////
/////////////////////////////////////////////////

var dataset2 = [3, 2, 3, 3, 5];
dataset2.sort();
var rgbValue2 = 180 / (Math.max.apply(null, dataset2));
var svgWidth = 800, svgHeight = 300, barPadding = 5;
var barWidth = (svgWidth / dataset2.length);

var svg2 = d3.select("#char2")
.attr("width", svgWidth)
.attr("height", svgHeight);

var yScale2 = d3.scaleLinear()
.domain([0, d3.max(dataset2)])
.range([0, svgHeight]);

var barChart = svg2.selectAll("rect")
.data(dataset2)
.enter()
.append("rect")
.attr("y", function (d) {
  return svgHeight - yScale2(d);
})
.attr("height", function (d) {
  return yScale2(d);
})
.attr("width", barWidth - barPadding)
.attr("transform", function (d, i) {
  let translate = [barWidth * i, 0];
  return "translate(" + translate + ")";
})
.attr("fill", function (d) {
  return "rgb(" + rgbValue2 * d + "," + rgbValue2 * d + "," + rgbValue2 * d
      + ")";
});

var text2 = svg2.selectAll("text")
.data(dataset2)
.enter()
.append("text")
.text(function (d) {
  return d;
})
.attr("y", function (d, i) {
  return svgHeight + 2;
})
.attr("x", function (d, i) {
  return barWidth * i;
})
.attr("fill", "#A64C38")

/////////////////////////////////////////////////
///////////////////Char 3////////////////////////
/////////////////////////////////////////////////



/////////////////////////////////////////////////
///////////////////Char 4////////////////////////
/////////////////////////////////////////////////