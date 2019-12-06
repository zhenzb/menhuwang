const gulp = require("gulp");
const scp = require("gulp-scp2");

gulp.task("upload", () => {
    console.log("Packing is complete and ready to upload remote server");
    return gulp
        .src("./dist/**/*")
        .pipe(
            scp({
                host: "39.96.59.248",
                username: "root",
                password: "Qihuang666",
                dest: "/usr/local/project/manager/dist",
                watch: function(client) {
                    client.on("write", function(o) {
                        console.log("Uploading--- %s", o.destination);
                    });
                }
            })
        )
        .on("error", err => {
            console.log(err);
        })
        .on("end", () => {
            console.log("Complete upload of all files");
        });
});
