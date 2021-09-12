<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/6
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <%--分页--%>
    <div id="page_nav">
        <c:if test="${requestScope.page.pageNo >1}">
            <a href="${requestScope.page.url}&pageNo=1">首页</a>
            <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
        </c:if>

        <c:choose>
            <%--情况一：总页码小于等于5--%>
            <c:when test="${requestScope.page.pageTotal<=5}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="${requestScope.page.pageTotal}"/>
            </c:when>
            <%--情况一：总页码大于5--%>
            <c:when test="${requestScope.page.pageTotal>5}">

                <c:choose >
                    <%--小情况1：当前页面为前面3个，页码范围是：1-5--%>
                    <c:when test="${requestScope.page.pageNo<=3}">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="5"/>
                    </c:when>
                    <%--小情况2：当前页面为最后3个，页码范围是：总页码减4-总页码--%>
                    <c:when test="${requestScope.page.pageNo>=requestScope.page.pageTotal-3}">
                        <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                        <c:set var="end" value="${requestScope.page.pageTotal}"/>
                    </c:when>

                    <%--小情况3：页码范围是：当前页码减2-当前页码加2--%>
                    <c:otherwise >
                        <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                        <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>

        <c:forEach begin="${begin}" end="${end}" var="i">
            <c:if test="${i==requestScope.page.pageNo}">
                [${i}]
            </c:if>
            <c:if test="${i!=requestScope.page.pageNo}">
                <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
            </c:if>
        </c:forEach>


        <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
            <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
            <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
        </c:if>

        共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
        <input id="serchpageBth" type="button" value="确定">

        <script type="text/javascript">
            $(function () {
                $("#serchpageBth").click(function () {

                    var pageno =$("#pn_input").val();

                    location.href="${requestScope.basePath}${requestScope.page.url}&pageNo="+pageno;

                })
            })
        </script>

    </div>
