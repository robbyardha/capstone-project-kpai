// This is for able to see chart. We are using Apex Chart. U can check the documentation of Apex Charts too..
var options = {
  series: [
    {
      name: "Keluarga",
      data: [44, 55, 57, 56, 61, 58, 63],
    },
    {
      name: "Percintaan",
      data: [76, 85, 101, 98, 87, 105, 91],
    },
    {
      name: "Anak Remaja",
      data: [35, 41, 36, 26, 45, 48, 52],
    },
    {
      name: "Pengembangan Diri",
      data: [44, 55, 57, 56, 61, 58, 63],
    },
    {
      name: "Trauma",
      data: [76, 85, 101, 98, 87, 105, 91],
    },
    {
      name: "Emosi",
      data: [35, 41, 36, 26, 45, 48, 52],
    },
    {
      name: "Bullying",
      data: [76, 85, 101, 98, 87, 105, 91],
    },
    {
      name: "Non Psikologis",
      data: [35, 41, 36, 26, 45, 48, 52],
    },
  ],
  chart: {
    type: "bar",
    height: 250, // make this 250
    sparkline: {
      enabled: true, // make this true
    },
  },
  plotOptions: {
    bar: {
      horizontal: false,
      columnWidth: "55%",
      endingShape: "rounded",
    },
  },
  dataLabels: {
    enabled: false,
  },
  stroke: {
    show: true,
    width: 2,
    colors: ["transparent"],
  },
  xaxis: {
    categories: ["Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"],
  },
  yaxis: {
    title: {
      text: "Pengadu",
    },
  },
  fill: {
    opacity: 1,
  },
  tooltip: {
    y: {
      formatter: function (val) {
        return val + " pengadu";
      },
    },
  },
};

var chart = new ApexCharts(document.querySelector("#apex1"), options);
chart.render();

// Sidebar Toggle Codes;

var sidebarOpen = false;
var sidebar = document.getElementById("sidebar");
var sidebarCloseIcon = document.getElementById("sidebarIcon");

function toggleSidebar() {
  if (!sidebarOpen) {
    sidebar.classList.add("sidebar_responsive");
    sidebarOpen = true;
  }
}

function closeSidebar() {
  if (sidebarOpen) {
    sidebar.classList.remove("sidebar_responsive");
    sidebarOpen = false;
  }
}
