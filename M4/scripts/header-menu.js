document.addEventListener("DOMContentLoaded", function() {
    const menuIcon = document.querySelector(".header-links-movile p");
    const navLinks = document.querySelector(".navlinks");
    const downloadpart = document.querySelector(".main-download");
    const seccionpart = document.querySelector(".secciones");
    const explicacionpart = document.querySelector(".explicacion");
    const shoppart = document.querySelector(".shop");
    const futureshoppart = document.querySelector(".future-shop");
    const welcome = document.querySelector(".welcome");
    const gamemechanics = document.querySelector(".gamemechanics");
    const objective = document.querySelector(".objective");
    const opcmenu = document.querySelector(".opcmenu");
    const tips = document.querySelector(".tips");
    const faq = document.querySelector(".FAQ");
    const h1main = document.querySelector("main > h1");
    const foot = document.querySelector("footer");
    const about = document.querySelector(".about");
    const meet = document.querySelector(".meet");
    const join = document.querySelector(".join");



    if (menuIcon && navLinks && downloadpart && explicacionpart && seccionpart) {
        menuIcon.addEventListener("click", function() {
            navLinks.classList.toggle("show");
            downloadpart.classList.toggle("nonedisplay");
            seccionpart.classList.toggle("nonedisplay");
            explicacionpart.classList.toggle("nonedisplay");
        });
    } else if (menuIcon && navLinks && shoppart && futureshoppart && h1main) {
        menuIcon.addEventListener("click", function() {
            navLinks.classList.toggle("show");
            shoppart.classList.toggle("nonedisplay");
            futureshoppart.classList.toggle("nonedisplay");
            h1main.classList.toggle("nonedisplay")
            foot.classList.toggle("nonedisplay")
        });
    } else if (menuIcon && navLinks && welcome && gamemechanics && objective && opcmenu && tips && faq) {
        menuIcon.addEventListener("click", function() {
            navLinks.classList.toggle("show");
            welcome.classList.toggle("nonedisplay");
            gamemechanics.classList.toggle("nonedisplay");
            objective.classList.toggle("nonedisplay");
            opcmenu.classList.toggle("nonedisplay");
            tips.classList.toggle("nonedisplay");
            faq.classList.toggle("nonedisplay");
        });
    } else if (menuIcon && navLinks && about && join && meet) {
        menuIcon.addEventListener("click", function() {
            navLinks.classList.toggle("show");
            about.classList.toggle("nonedisplay");
            join.classList.toggle("nonedisplay");
            meet.classList.toggle("nonedisplay");
        });
    } else {
        console.error("Uno o más elementos no se encontraron en el DOM");
    }
});
