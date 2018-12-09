
angular.module('Controllers', [])
.controller('NavController', ['$scope', function ($scope) {
	// 定义导航
	$scope.navs = [
		{link: '#/today', text: '今日一刻', icon: 'icon-home'},
		{link: './index.html#!/older', text: '影院热播', icon: 'icon-file-empty'},
		{link: './index.html#!/author', text: '豆瓣书籍', icon: 'icon-pencil'},
		{link: './index.html#!/category', text: '天气预报', icon: 'icon-menu'},
	    {link: './index.html#!/favourite', text: '我的喜欢', icon: 'icon-heart'},
		{link: './index.html#!/settings', text: '设置', icon: 'icon-cog'},
	];
}])
.controller('TodayController', ['$scope', '$rootScope', '$http', function ($scope, $rootScope, $http) {
	$rootScope.date=new Date(); 
	$rootScope.index = 0;
	$rootScope.title = '快乐一刻';
	$rootScope.loaded = false;
	$http({
		url:'https://raw.githubusercontent.com/wangwangla/biji/master/1.json',
	  //url:'http://zhouxunwang.cn/data/?id=34&key=BrvOrdNmT9r+h5mB8I0zT2/BOQTgsJeZ/pxx6Q&sort=%E7%AC%91%E8%AF%9D&time=1418816972',
	  //时间戳：var timestamp = Date.parse(new Date());
	  method: 'get'
	}).then(function ( info ){
		//console.log(info.data.result.data[0].content);
		$scope.posts = info.data.result.data;
		console.log($scope.posts.data);
		$rootScope.loaded = true;
});
}])
// 影院热播
.controller('OlderController', ['$scope', '$rootScope', '$http', function ($scope, $rootScope, $http) {
	$rootScope.index = 1;
	$rootScope.title = '影院热播';
	$rootScope.loaded = false;
	var myUrl="http://api.douban.com/v2/movie/in_theaters?city=西安";

jsonp(myUrl, {}, function(data) {
	console.log(data);
	var title = data.title;
	$scope.addr=title.substring(8,title.length);
	$scope.posts=data.subjects;
	$rootScope.loaded = true;
});
}])

// 热门作者
.controller('AuthorController', ['$scope', '$rootScope', '$http', function ($scope, $rootScope, $http) {

	$rootScope.index = 2;
	$rootScope.title = '豆瓣书籍';
	$rootScope.loaded = false;
   	var myUrl='https://api.douban.com/v2/book/search?q=javascript';        		

jsonp(myUrl, {}, function(data) {
	console.log(data);
	console.log(data.books);
	$scope.posts=data.books;
	$rootScope.loaded = true;
});
}])

// 栏目浏览
.controller('CategoryController', ['$scope', '$rootScope', '$http', function ($scope, $rootScope, $http) {
	 var u1="https://free-api.heweather.com/v5/weather?city=";
    var u2;
    var u3="&key=545d63e185fc48169a43cbabba6e74d2";
	$rootScope.index = 3;
	$rootScope.title = '天气预报';
	$rootScope.loaded = false;
 $scope.city="西安";
      $scope.show=false;
          u2=$scope.city;
          $scope.show=true;
          $http({
            url:u1+u2+u3
          }).then(function (data) {
          	$scope.post=data.data.HeWeather5;
            /*$scope.cityName=data.data.HeWeather5[0].basic.city;*/
            /*$scope.date=data.data.HeWeather5[0].daily_forecast[0].date;
            $scope.mTemp=data.data.HeWeather5[0].daily_forecast[0].tmp.max;
            $scope.xTemp=data.data.HeWeather5[0].daily_forecast[0].tmp.min;*/
          	$rootScope.loaded = true;
          })  
    }])
.controller('SettingsController', ['$scope', '$rootScope', function ($scope, $rootScope) {
	$rootScope.index = 4;
	$rootScope.title = '设置';
	$rootScope.loaded = true;
}]);












	function jsonp(url, data, fn) {
	function getData(data) {
		fn(data);
	}
	window['getData'] = getData;
	// 处理data数据
	var searchData = '';
	if(data.length) {
		searchData = "?" + JSON.stringify(data);
	}else {
		searchData = "&";
	}
	// 1. 创建script标签
	var script = document.createElement("script");
	// 2. 加入callback = 
	script.src = url + searchData + "callback=getData";
	// 3. 插入标签
	console.log(document.body.appendChild(script));
}