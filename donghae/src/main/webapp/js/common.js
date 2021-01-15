$(function() {
	//menu
   $(".depth1 a").hover(
   function() {
        $(".depth2").stop().slideDown(400);
    },
      function() {
       $(".depth2").stop().slideUp(400);
     }
   );
    $(".depth2").hover(
      function() {
        $(".depth2").stop().slideDown(400);
     },
      function() {
        $(".depth2").stop().slideUp(400);
     }
   );

//languages
	  $("#gnb dl dt").click(function(){
		$("#gnb dl dd").slideToggle("200");
	  });

//mainvisual
    $('.mvisual').bxSlider({
        auto: true, // �ڵ����� �ִϸ��̼� ����
        speed: 500,  // �ִϸ��̼� �ӵ�
        pause: 5000,  // �ִϸ��̼� ���� �ð� (1000�� 1��)
        mode: 'fade', // �����̵� ��� ('fade', 'horizontal', 'vertical' �� ����)
        autoControls: false, // ���� �� ������ư ������
        pager: false, // ������ ǥ�� ������
        captions: false, // �̹��� ���� �ؽ�Ʈ�� ���� �� ����
		touchEnabled : (navigator.maxTouchPoints > 0)
    });

//event
  var owl = $('.owl-carousel');
  owl.owlCarousel({
	margin: 15,
	center: true,
	nav: true,
	loop: true,
    autoplay:true,
    autoplayTimeout:3000,
	responsive: {
        0:{
            items:1
        },
        400:{
            items:2
        },
        600:{
            items:2
        },
        1000:{
            items:3
        }
    }
  });

//back_top
	$( '.back_top a' ).click( function() {
	  $( 'html, body' ).animate( { scrollTop : 0 }, 400 );
	  return false;
	} );

  } );
  $(document).ready(function(){
    mobile_menu();
});

function mobile_menu(){
    /* ���� ���� */
    var $menu = null;
    var $left_gnb = null; // ���� ��ü
    var $depth1_wrap = null;
    var $depth1 = null;
    var $depth1_btn = null;
    
    /* ���� �Լ� */
    function start(){
        init();
        init_event();
    }
    /* ���� �ʱ�ȭ �Լ� */
    function init(){
        $menu = $('.all_menu');
        $left_gnb = $('.lnb_wrap'); // ���� ��ü
        $depth1_wrap = $('.left_lnb>li');
        $depth1 = $depth1_wrap.children('ul');
        $depth1_btn = $depth1_wrap.children('a');
    }
    /* �̺�Ʈ �Լ� */
    function init_event(){
        
        /* ����� �޴� ��ư Ŭ�������� ����� �޴����� ������ �ϱ� */
        $menu.click(function(event){
            event.preventDefault();
            $left_gnb.addClass('on');
        });
        
        /* x��ư �������� ����� �޴� �ݱ� */
        $('.close').click(function(event){
            event.preventDefault();

            $left_gnb.removeClass('on');
            
            // x��ư ������ �ð��� �ణ�ΰ� �Ҹ޴� ������ �ϱ�
            setTimeout(function(){
                $depth1_btn.removeClass('on');
                $depth1.slideUp();
            },300)
        });
        
        /* depth1�� ���޴� Ŭ���� depth2 ������ �ϱ� 
        $depth1_btn.click(function(event){
            event.preventDefault();
            var $this = $(this);
            var $this_ul = $this.siblings('ul');

            var check = $this.hasClass('on');
            if(check){
                $this.removeClass('on');
                $this_ul.stop(true,true).slideUp();
            }else{
                $depth1_btn.removeClass('on');
                $depth1.stop().slideUp();
                $this.addClass('on');
                $this_ul.stop(true,true).slideDown();
            }
        });*/
        
        /* ����̽� ũ�� ����� ����� �޴����� ����� */
       // $(window).resize(function(){
          //  $left_gnb.removeClass('on');
        //});
    }
    
    start(); // ���� ȣ��
}

$(document).ready(function(){
	
	$('ul.tab li').click(function(){
		var tab_id = $(this).attr('data-tab');

		$('ul.tab li').removeClass('on');
		$('.tab_cont').removeClass('on');

		$(this).addClass('on');
		$("#"+tab_id).addClass('on');
	})

//select_box
var x, i, j, selElmnt, a, b, c;
/*look for any elements with the class "custom-select":*/
x = document.getElementsByClassName("delevery-select");
for (i = 0; i < x.length; i++) {
  selElmnt = x[i].getElementsByTagName("select")[0];
  /*for each element, create a new DIV that will act as the selected item:*/
  a = document.createElement("DIV");
  a.setAttribute("class", "select-selected");
  a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
  x[i].appendChild(a);
  /*for each element, create a new DIV that will contain the option list:*/
  b = document.createElement("DIV");
  b.setAttribute("class", "select-items select-hide");
  for (j = 1; j < selElmnt.length; j++) {
    /*for each option in the original select element,
    create a new DIV that will act as an option item:*/
    c = document.createElement("DIV");
    c.innerHTML = selElmnt.options[j].innerHTML;
    c.addEventListener("click", function(e) {
        /*when an item is clicked, update the original select box,
        and the selected item:*/
        var y, i, k, s, h;
        s = this.parentNode.parentNode.getElementsByTagName("select")[0];
        h = this.parentNode.previousSibling;
        for (i = 0; i < s.length; i++) {
          if (s.options[i].innerHTML == this.innerHTML) {
            s.selectedIndex = i;
            h.innerHTML = this.innerHTML;
            y = this.parentNode.getElementsByClassName("same-as-selected");
            for (k = 0; k < y.length; k++) {
              y[k].removeAttribute("class");
            }
            this.setAttribute("class", "same-as-selected");
            break;
          }
        }
        h.click();
    });
    b.appendChild(c);
  }
  x[i].appendChild(b);
  a.addEventListener("click", function(e) {
      /*when the select box is clicked, close any other select boxes,
      and open/close the current select box:*/
      e.stopPropagation();
      closeAllSelect(this);
      this.nextSibling.classList.toggle("select-hide");
      this.classList.toggle("select-arrow-active");
    });
}
function closeAllSelect(elmnt) {
  /*a function that will close all select boxes in the document,
  except the current select box:*/
  var x, y, i, arrNo = [];
  x = document.getElementsByClassName("select-items");
  y = document.getElementsByClassName("select-selected");
  for (i = 0; i < y.length; i++) {
    if (elmnt == y[i]) {
      arrNo.push(i)
    } else {
      y[i].classList.remove("select-arrow-active");
    }
  }
  for (i = 0; i < x.length; i++) {
    if (arrNo.indexOf(i)) {
      x[i].classList.add("select-hide");
    }
  }
}
/*if the user clicks anywhere outside the select box,
then close all select boxes:*/
document.addEventListener("click", closeAllSelect);


$(function() {
    //----- OPEN
    $('[data-popup-open]').on('click', function(e)  {
        var targeted_popup_class = jQuery(this).attr('data-popup-open');
        $('[data-popup="' + targeted_popup_class + '"]').fadeIn(350);

        e.preventDefault();
    });

    //----- CLOSE
    $('[data-popup-close]').on('click', function(e)  {
        var targeted_popup_class = jQuery(this).attr('data-popup-close');
        $('[data-popup="' + targeted_popup_class + '"]').fadeOut(350);

        e.preventDefault();
    });
});

})


$(document).ready(function(){
  $(".m-gnb ul li a.korea").click(function(){
    $(".m-gnb ul li ul").slideToggle();
  });

  $(".pbtn").click(function(){
	$(".phide").slideToggle("slow");
  });

});