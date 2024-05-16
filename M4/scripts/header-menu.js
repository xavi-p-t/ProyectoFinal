document.addEventListener("DOMContentLoaded", function() {
    const menuIcon = document.querySelector(".header-links-movile p");
    const navLinks = document.querySelector(".navlinks");
    const downloadpart = document.querySelector(".main-download");
    const seccionpart = document.querySelector(".secciones");
    const explicacionpart = document.querySelector(".explicacion");
    const shoppart = document.querySelector(".shop");
    const futureshoppart = document.querySelector(".future-shop");

    if (menuIcon && navLinks && downloadpart && explicacionpart && seccionpart) {
        menuIcon.addEventListener("click", function() {
            navLinks.classList.toggle("show");
            downloadpart.classList.toggle("nonedisplay");
            seccionpart.classList.toggle("nonedisplay");
            explicacionpart.classList.toggle("nonedisplay");
        });
    } else if (menuIcon && navLinks && shoppart && futureshoppart) {
        menuIcon.addEventListener("click", function() {
            navLinks.classList.toggle("show");
            shoppart.classList.toggle("nonedisplay");
            futureshoppart.classList.toggle("nonedisplay");
        });
    } else {
        console.error("Uno o más elementos no se encontraron en el DOM");
    }
});
