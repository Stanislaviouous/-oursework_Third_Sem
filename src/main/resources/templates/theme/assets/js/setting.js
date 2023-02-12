var cart = {};


$('document').ready(function(){
    
    loadGoods();
    checkCart();
    showMiniCart();
    });
    function loadGoods(){
        $.getJSON('assets/goods.json', function (data){
           
            
            var out = '';
            var html = '';
            
            data.forEach(({ id, title, link, price }) => {
                
               document.getElementById('goods').innerHTML = html;
               html = html + '<li> <div class="col-lg-4 col-md-4 col-sm-4 gallery">'+
               '  <div class="name" data-id="'+title+'">'  + title + 
               '</div> <img src="' + link + 
               '" class="img-responsive" data-id="'+ link+'"> <div class="price" data-id="'+price+'">'+price+'<p>руб.</p></div>  '+
                '<div class="addand"><button class="plus" data-id="'+ title+'"><b>Добавить в корзину</b></button>'+
                ''+
                '  </div> </li>'
               
            })
            
            $('button.plus').on('click', addToCart);
             
        })
function addToCart() {
    var id =  $(this).attr('data-id');
    
    if (cart[id] == undefined){
        cart[id] = 1  ;
        
        console.log(cart);
       
    }
    else{
    cart[id]++;
    console.log(cart);
    }
    
    localStorage.setItem('cart', JSON.stringify(cart) );
    showMiniCart();
}


    

function checkCart (){
     if (localStorage.getItem('cart') != null){
        cart = JSON.parse(localStorage.getItem('cart'));
     }
    }
function showMiniCart(){
    var out ='';
    out +='<p>Ваш чек будет выгледеть так:</p><form>';
    for (var w in cart){
        
        out +=w +'  '+ '---'+ '  '+ cart[w]+' шт.'+'<button class="minus" id="minus" type="button"  ><b>‐<b></button></br>' ;
       
        

    }
    out +='<p>Введите ваш адрес:</p>'
    out +='<form><input class="adres" type="text" style="color:black" placeholder="Адрес" id="adre"> ';
    out +='</br>';
    out +='<button type="button" class="order" id="order">Заказать</button></form>';
   
    
    
    $('#mini-cart').html(out); 
    
    
}




function orderSend() {

   alert("Ваш заказ передан в службу доставки");

   
 (function() {
    var d = document,
      inp = d.getElementsByClassName('adres'),
      mas = [];
      if (localStorage.getItem('cart') != null){
        orders = JSON.parse(localStorage.getItem('cart'));
     }
    function save() {
      for (var i = 0; i < inp.length; i++) {
        mas[i] = inp[i].value;
      }
      
      console.log(mas);
      localStorage.setItem('orders', JSON.stringify(orders) + JSON.stringify(mas));
      console.log(orders);
    }
    save();
  })();
 

  

}

document.onclick = event =>{
    if (event.target.classList.contains('order')){
        orderSend(event.target.dataset.id);
    }
    
}
    
}






