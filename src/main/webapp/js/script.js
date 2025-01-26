$(document).ready(function (){
    let urlParams = new URLSearchParams(window.location.search);
    let name = urlParams.get('value1');
    let id = urlParams.get('value2');

    $('#id-user').text(name);
    $('#log-out').text("LOG OUT");

    $.ajax({
        url: 'http://localhost:8080/easyBird_E_Commerce/flashSaleItemGet',
        type: "GET",
        success: (res) => {
            setSaleItem(res.data);
        },
        error: (error) => {
            console.log(error)
        }

    });

    $.ajax({
        url: 'http://localhost:8080/easyBird_E_Commerce/product-view',
        type: "GET",
        success: (res) => {
            setProductItem(res.data);
        },
        error: (error) => {
            console.log(error)
        }

    });

});

const setProductItem = (data) =>{

}

const setSaleItem = (data) =>{
  data.forEach((saleItem)=>{
      let data = `
                <div class="sale-item">
                <div class="img-box">
                <img class="" src="${saleItem.imageUrl}" alt="">
                </div>
                <h6>${saleItem.title}</h6>
                <h4 class="main-price">$.${saleItem.price}<sup>${saleItem.priceDecimal}</sup></h4>
                <h6 class="original-price">$.${saleItem.originalPrice}</h6>
                <div class="offer-tag">Save <span>${saleItem.discount}%</span></div>
                <button id="add-to-cart-btn" class="btn-01" type="button">Add to cart</button>
                </div>`;
      $('#slide-container').append(data);
  })
};

const makeUserAcc = (id, name) => {
    const uppercaseName = name.toUpperCase();
    window.location.href = 'user.jsp?value1=' + encodeURIComponent(uppercaseName) + '&value2=' + encodeURIComponent(id);
    };

$('#navigate-cart').on('click', ()=>{
    window.location.href = "cart.jsp";
})

$('#add-to-cart-btn').on('click',(e)=>{
    e.preventDefault();

    $.ajax({

        url:'http://localhost:8080/easyBird_E_Commerce/cart',
        type:"POST",
        headers:{
            "Content-Type": "application/json"
        },
        data : JSON.stringify({
            "ID": "1",
            "NAME": "rashmika"
        }),
        success:{},

        error:(err)=>{
            console.log(err);
        }

    })

})

$('#log-in-btn').on('click',(e)=>{
    e.preventDefault()
    let number = $('#mobile').val();
    let password = $('#password').val();

    $.ajax({
            url: `http://localhost:8080/easyBird_E_Commerce/user-check?number=${number}&password=${password}`,
            type: "GET",
            success: (res) => {

                Swal.fire({
                    icon: res.alert,
                    text: res.alertMessage
                }).then((result) => {
                    if (result.isConfirmed) {
                        makeUserAcc(res.id, res.name);
                    }
                });

            },
            error: () => {
                Swal.fire({
                    icon: res.alert,
                    text: res.alertMessage
                });
            }

    });

})


