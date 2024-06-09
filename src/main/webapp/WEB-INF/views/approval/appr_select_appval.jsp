<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>결재라인선택</title>
    <link rel="stylesheet" href="<c:url value='../css/approval/appr_boardList.css'/> ">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="https://code.highcharts.com/modules/networkgraph.js"></script>
    <script src="https://code.highcharts.com/modules/exporting.js"></script>
    <script src="https://code.highcharts.com/modules/accessibility.js"></script>
</head>
<body>
    <figure class="highcharts-figure">
        <div class="container" id="container"></div>
    </figure>


</body>
<script>
    $(document).ready(function (){
        // $(window).load(function() {
        //
        //     var strWidth = $('#container').outerWidth() + (window.outerWidth - window.innerWidth);
        //
        //     var strHeight = $('#container').outerHeight() + (window.outerHeight - window.innerHeight);
        //
        //     //resize
        //     window.resizeTo( strWidth, strHeight );
        //
        //
        // });
        //
        // function func_networkgrqaph(){

        // }
        networkgragh();

        function networkgragh(){
            // 차트에 출력할 배열
            let dataArr = [];

            $.ajax({
                url: '/approval/get_appr_target_list',
                type : 'GET',

                success: function (result){
                    $.each(result, function (index, name){
                        dataArr.push(result.user_name);
                        dataArr.push(result.level_name);
                    })

                    Highcharts.chart('container', {
                        chart: {
                            type: 'networkgraph',
                            height: '100%'
                        },
                        title: {
                            text: '조직도'
                        },
                        subtitle: {
                        },
                        plotOptions: {
                            networkgraph: {
                                keys: ['from', 'to'],
                                layoutAlgorithm: {
                                    enableSimulation: true,
                                    friction: -0.9
                                }
                            }
                        },
                        series: [{
                            accessibility: {
                                enabled: false
                            },
                            dataLabels: {
                                enabled: true,
                                linkFormat: ''
                            },
                            id: 'lang-tree',
                            data: dataArr
                        }]
                    });
                },
                error:function (request, status, error){
                    console.log(request.status);
                    console.log(request.responseText);
                    console.log(error);

                    alert("등록 실패 - 관리자에게 문의하세요")
                }
            })
        }

    })
</script>
</html>
