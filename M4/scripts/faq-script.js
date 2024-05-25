document.addEventListener("DOMContentLoaded", function() {
    const btns = document.querySelectorAll(".boton");
    const respuestas = document.querySelectorAll(".respuesta");

    btns.forEach((btn, index) => {
        btn.addEventListener("click", function() {
            respuestas[index].classList.toggle("open");
            if (respuestas[index].classList.contains("open")) {
                btn.innerHTML = "&#9650;"; // Flecha hacia arriba cuando se desplega
            } else {
                btn.innerHTML = "&#9660;"; // Flecha hacia abajo o default
            }
        });
    });
});
