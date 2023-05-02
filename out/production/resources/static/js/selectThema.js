(function() {
  $.fn.extend({

    roulette: function(options) {

      var defaults = {
        angle: 0,
        angleOffset: -45,
        speed: 5000,
        easing: "easeInOutElastic",
      };

      var opt = $.extend(defaults, options);

      return this.each(function() {
        var o = opt;

        var data = [
					{
            color: '#FFE4B5',
            text: '로맨스'
          },
          {
            color: '#98FB98',
            text: '무협'
          },
          {
            color: '#87CEFA',
            text: '여행'
          },
          {
            color: '#DDA0DD',
            text: '역사'
          }
        ];

        var $wrap = $(this);
        var $btnStart = $wrap.find("#btn-start");
        var $roulette = $wrap.find(".roulette");
        var wrapW = $wrap.width();
        var angle = o.angle;
        var angleOffset = o.angleOffset;
        var speed = o.speed;
        var esing = o.easing;
        var itemSize = data.length;
        var itemSelector = "item";
        var labelSelector = "label";
        var d = 360 / itemSize;
        var borderTopWidth = wrapW;
        var borderRightWidth = tanDeg(d);

        for (i = 1; i <= itemSize; i += 1) {
          var idx = i - 1;
          var rt = i * d + angleOffset;
          var itemHTML = $('<div class="' + itemSelector + '">');
          var labelHTML = '';
              labelHTML += '<p class="' + labelSelector + '">';
              labelHTML += '	<span class="text">' + data[idx].text + '<\/span>';
              labelHTML += '<\/p>';

          $roulette.append(itemHTML);
          $roulette.children("." + itemSelector).eq(idx).append(labelHTML);
          $roulette.children("." + itemSelector).eq(idx).css({
            "left": wrapW / 2,
            "top": -wrapW / 2,
            "border-top-width": borderTopWidth,
            "border-right-width": borderRightWidth,
            "border-top-color": data[idx].color,
            "transform": "rotate(" + rt + "deg)"
          });

          var textH = parseInt(((2 * Math.PI * wrapW) / d) * .5);

          $roulette.children("." + itemSelector).eq(idx).children("." + labelSelector).css({
            "height": textH + 'px',
            "line-height": textH + 'px',
            "transform": 'translateX(' + (textH * 1.3) + 'px) translateY(' + (wrapW * -.3) + 'px) rotateZ(' + (90 + d * .5) + 'deg)'
          });

        }

        function tanDeg(deg) {
          var rad = deg * Math.PI / 180;
          return wrapW / (1 / Math.tan(rad));
        }


        $btnStart.on("click", function() {
          rotation();
        });

        function rotation() {

//          var completeA = 360 * r(5, 10) + r(0, 360);
          var ran = Math.floor(Math.random() * data.length);
          var arc = 360 / data.length;
          var rotate1 = (ran * arc) + 3600 + (arc * 3) - (arc/4);

          $roulette.rotate({
            angle: angle,
//            animateTo: completeA,
            animateTo: rotate1,
            center: ["50%", "50%"],
            easing: $.easing.esing,
            callback: function() {
              var currentA = $(this).getRotateAngle();

              console.log(currentA);
              //결과
              endAnimate(currentA);
            },
            duration: speed
          });
        }

        function endAnimate($currentA) {
            var real_angle = $currentA;

            while (real_angle >= 360){
                real_angle -= 360;
            }

            if(real_angle < 90){
               alert("오늘의 추천 테마는 여행!");
            }
            else if(real_angle >= 90 && real_angle < 180){
               alert("오늘의 추천 테마는 무협!");
            }
            else if(real_angle >= 180 && real_angle < 270){
               alert("오늘의 추천 테마는 로맨스!");
            }
            else if(real_angle >= 270 && real_angle < 360){
               alert("오늘의 추천 테마는 역사!");
            }

//            postAddPoint(point);
            return;
        }

        function r(min, max) {
          return Math.floor(Math.random() * (max - min + 1)) + min;
        }

          function postAddPoint($point) {
              const form = document.createElement('form');
              form.method = 'get';
              form.action = '/views/event/addRoulettePoint';

              var input1 = document.createElement('input');
              input1.setAttribute("type", "hidden");
              input1.setAttribute("name", "pointData");
              input1.setAttribute("value", $point);
              form.appendChild(input1);

              document.body.appendChild(form)
              form.submit();
          }
      });
    }
  });
})(jQuery);


$(function() {

  $('.box-roulette').roulette();

});