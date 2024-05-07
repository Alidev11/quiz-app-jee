let sidebar = document.querySelector(".sidebar");
let closeBtn = document.querySelector("#btn");

closeBtn.addEventListener("click", () => {
    sidebar.classList.toggle("open");
    menuBtnChange(); //calling the function(optional)
});

// following are the code to change sidebar button(optional)
function menuBtnChange() {
    if (sidebar.classList.contains("open")) {
        closeBtn.classList.replace("bx-menu", "bx-menu-alt-right"); //replacing the iocns class
    } else {
        closeBtn.classList.replace("bx-menu-alt-right", "bx-menu"); //replacing the iocns class
    }
}

$(".prof_span").css("background-color", "#2c2cd8");

$(".prof_span").click(function profClick() {
    $(".prof_span").css("background-color", "#2c2cd8");
    $(".cord_span").css("background-color", "#11101d");
    $(".chDept_span").css("background-color", "#11101d");
    $(".agt_sch_span").css("background-color", "#11101d");
    $(".matiere_span").css("background-color", "#11101d");
    $(".permission_span").css("background-color", "#11101d");

    $("#myTable_cord").fadeOut(1);
    $("#myTable_chef_dept").fadeOut(1);
    $("#myTable_agent_scholarite").fadeOut(1);
    $("#myTable_matiere").fadeOut(1);
    $("#myTable").fadeIn(800);
    $("#myTable_list_mat").fadeOut(1);
    $(".ajouter_mat").fadeOut(1);
    $("#permission_checks").fadeIn(800);
    $(".ajouter_user").fadeIn(800);
    $(".ajouter_perm_role").fadeOut(1);
    $(".ajouter_perm").fadeOut(1);
    $("#myTable_list_perm").fadeOut(1);
    $("#myTable_permission_role").fadeOut(1);
});

$(".cord_span").click(function cord_click() {
    $(".cord_span").css("background-color", "#2c2cd8");
    $(".prof_span").css("background-color", "#11101d");
    $(".chDept_span").css("background-color", "#11101d");
    $(".agt_sch_span").css("background-color", "#11101d");
    $(".matiere_span").css("background-color", "#11101d");
    $(".permission_span").css("background-color", "#11101d");

    $("#myTable").fadeOut(1);
    $("#myTable_chef_dept").fadeOut(1);
    $("#myTable_agent_scholarite").fadeOut(1);
    $("#myTable_matiere").fadeOut(1);
    $("#myTable_cord").fadeIn(800);
    $("#myTable_list_mat").fadeOut(1);
    $(".ajouter_mat").fadeOut(1);
    $("#permission_checks").fadeIn(800);
    $(".ajouter_user").fadeIn(800);
    $(".ajouter_perm_role").fadeOut(1);
    $(".ajouter_perm").fadeOut(1);
    $("#myTable_list_perm").fadeOut(1);
    $("#myTable_permission_role").fadeOut(1);
});

$(".chDept_span").click(function chef_dept_click() {
    $(".chDept_span").css("background-color", "#2c2cd8");
    $(".prof_span").css("background-color", "#11101d");
    $(".cord_span").css("background-color", "#11101d");
    $(".agt_sch_span").css("background-color", "#11101d");
    $(".matiere_span").css("background-color", "#11101d");
    $(".permission_span").css("background-color", "#11101d");

    $("#myTable").fadeOut(1);
    $("#myTable_cord").fadeOut(1);
    $("#myTable_agent_scholarite").fadeOut(1);
    $("#myTable_matiere").fadeOut(1);
    $("#myTable_chef_dept").fadeIn(800);
    $("#myTable_list_mat").fadeOut(1);
    $(".ajouter_mat").fadeOut(1);
    $("#permission_checks").fadeIn(800);
    $(".ajouter_user").fadeIn(800);
    $(".ajouter_perm_role").fadeOut(1);
    $(".ajouter_perm").fadeOut(1);
    $("#myTable_list_perm").fadeOut(1);
    $("#myTable_permission_role").fadeOut(1);
});

$(".agt_sch_span").click(function agt_sch_click() {
    $(".agt_sch_span").css("background-color", "#2c2cd8");
    $(".prof_span").css("background-color", "#11101d");
    $(".chDept_span").css("background-color", "#11101d");
    $(".cord_span").css("background-color", "#11101d");
    $(".matiere_span").css("background-color", "#11101d");
    $(".permission_span").css("background-color", "#11101d");

    $("#myTable").fadeOut(1);
    $("#myTable_chef_dept").fadeOut(1);
    $("#myTable_cord").fadeOut(1);
    $("#myTable_matiere").fadeOut(1);
    $("#myTable_agent_scholarite").fadeIn(800);
    $("#myTable_list_mat").fadeOut(1);
    $(".ajouter_mat").fadeOut(1);
    $("#permission_checks").fadeIn(800);
    $(".ajouter_user").fadeIn(800);
    $(".ajouter_perm_role").fadeOut(1);
    $(".ajouter_perm").fadeOut(1);
    $("#myTable_list_perm").fadeOut(1);
    $("#myTable_permission_role").fadeOut(1);
});

$(".matiere_span").click(function matiere_click() {
    $(".matiere_span").css("background-color", "#2c2cd8");
    $(".prof_span").css("background-color", "#11101d");
    $(".chDept_span").css("background-color", "#11101d");
    $(".cord_span").css("background-color", "#11101d");
    $(".agt_sch_span").css("background-color", "#11101d");
    $(".permission_span").css("background-color", "#11101d");

    $("#myTable").fadeOut(1);
    $("#myTable_chef_dept").fadeOut(1);
    $("#myTable_cord").fadeOut(1);
    $("#myTable_agent_scholarite").fadeOut(1);
    $("#myTable_matiere").fadeIn(800);
    $("#myTable_list_mat").fadeIn(800);
    $(".ajouter_mat").fadeIn(50);
    $("#permission_checks").fadeOut(1);
    $(".ajouter_user").fadeOut(1);
    $(".ajouter_perm_role").fadeOut(1);
    $(".ajouter_perm").fadeOut(1);
    $("#myTable_list_perm").fadeOut(1);
    $("#myTable_permission_role").fadeOut(1);
});

$(".permission_span").click(function matiere_click() {
    $(".permission_span").css("background-color", "#2c2cd8");
    $(".matiere_span").css("background-color", "#11101d");
    $(".prof_span").css("background-color", "#11101d");
    $(".chDept_span").css("background-color", "#11101d");
    $(".cord_span").css("background-color", "#11101d");
    $(".agt_sch_span").css("background-color", "#11101d");

    $("#myTable").fadeOut(1);
    $("#myTable_chef_dept").fadeOut(1);
    $("#myTable_cord").fadeOut(1);
    $("#myTable_agent_scholarite").fadeOut(1);
    $("#myTable_matiere").fadeOut(1);
    $("#myTable_list_mat").fadeOut(1);
    $("#myTable_list_mat").fadeOut(1);
    $(".ajouter_user").fadeOut(1);
    $(".ajouter_mat").fadeOut(1);
    $(".ajouter_perm_role").fadeIn(50);
    $(".ajouter_perm").fadeIn(50);
    $("#myTable_list_perm").fadeIn(800);
    $("#myTable_permission_role").fadeIn(800);
});

$(".ajouter_QA").click(function assignValue() {
    var quizId = $(this).data('id');
    console.log(quizId);
    $('#add-form1\\:quizIdInput').val(quizId);
});
