const base = {
    get() {
        return {
            url : "http://localhost:8080/cheweizulinxitong/",
            name: "cheweizulinxitong",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/cheweizulinxitong/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "车位租赁系统"
        } 
    }
}
export default base
