<template>
  <div :class="{fullscreen:fullscreen}" class="tinymce-container editor-container">
    <textarea :id="tinymceId" class="tinymce-textarea"/>
  </div>
</template>

<script>
import {uploadFile } from "../../api/file/upload";

export default {
    name: "Tinymce",
    props: {
        id: {
            type: String,
            default: function() {
                return (
                    "vue-tinymce-" +
                    +new Date() +
                    ((Math.random() * 1000).toFixed(0) + "")
                );
            }
        },
        value: {
            type: String,
            default: ""
        },
        toolbar: {
            type: Array,
            required: false,
            default() {
                return [
                    "searchreplace bold italic underline strikethrough alignleft aligncenter alignright outdent indent | blockquote undo redo removeformat subscript superscript code codesample",
                    "hr bullist numlist link image charmap preview anchor pagebreak | insertdatetime media table forecolor backcolor fullscreen"
                ];
            }
        },
        plugins: {
            type: Array,
            required: false,
            default() {
                return [
                    "advlist anchor autolink autosave code codesample colorpicker colorpicker contextmenu directionality emoticons fullscreen hr image imagetools importcss insertdatetime link lists media nonbreaking noneditable pagebreak paste preview print save searchreplace spellchecker tabfocus table template textcolor textpattern visualblocks visualchars wordcount"
                ];
            }
        },
        menubar: {
            type: String,
            default: "file edit insert view format table"
        },
        height: {
            type: Number,
            required: false,
            default: 360
        }
    },
    data() {
        return {
            hasChange: false,
            hasInit: false,
            tinymceId: this.id,
            fullscreen: false
        };
    },
    computed: {},
    watch: {
        value(val) {
            if (!this.hasChange && this.hasInit) {
                this.$nextTick(() =>
                    window.tinymce.get(this.tinymceId).setContent(val || "")
                );
            }
        }
    },
    mounted() {
        this.initTinymce();
    },
    activated() {
        this.initTinymce();
    },
    deactivated() {
        this.destroyTinymce();
    },
    destroyed() {
        this.destroyTinymce();
    },
    methods: {
        initTinymce() {
            const _this = this;
            window.tinymce.init({
                language: "zh_CN",
                selector: `#${this.tinymceId}`,
                height: this.height,
                body_class: "panel-body",
                object_resizing: false,
                toolbar: this.toolbar,
                menubar: this.menubar,
                plugins: this.plugins,
                end_container_on_empty_block: true,
                powerpaste_word_import: "clean",
                code_dialog_height: 450,
                code_dialog_width: 1000,
                // advlist_bullet_styles: "default",
                // advlist_number_styles: "default",
                imagetools_cors_hosts: ["www.tinymce.com", "codepen.io"],
                default_link_target: "_blank",
                link_title: false,
                nonbreaking_force_tab: true, // inserting nonbreaking space &nbsp; need Nonbreaking Space Plugin
                convert_urls:false,
                init_instance_callback: editor => {
                    if (_this.value) {
                        editor.setContent(_this.value);
                    }
                    _this.hasInit = true;
                    editor.on("NodeChange Change KeyUp SetContent", () => {
                        this.hasChange = true;
                        this.$emit("input", editor.getContent());
                    });
                },
                setup(editor) {
                    editor.on("FullscreenStateChanged", e => {
                        _this.fullscreen = e.state;
                    });
                },
                // 整合七牛上传
                // images_dataimg_filter(img) {
                //   setTimeout(() => {
                //     const $image = $(img);
                //     $image.removeAttr('width');
                //     $image.removeAttr('height');
                //     if ($image[0].height && $image[0].width) {
                //       $image.attr('data-wscntype', 'image');
                //       $image.attr('data-wscnh', $image[0].height);
                //       $image.attr('data-wscnw', $image[0].width);
                //       $image.addClass('wscnph');
                //     }
                //   }, 0);
                //   return img
                // },
                images_upload_handler(blobInfo, success, failure, progress) {
                    // formData.append("file", blobInfo.blob());
                    // qiniuUpload(formData)
                    //     .then(response => {
                    //         if (response.data.filePath) {
                    //             success(response.data.filePath);
                    //             progress(100);
                    //             return;
                    //         }
                    //         failure("上传出错");
                    //     })
                    //     .catch(() => {
                    //         failure("上传出错");
                    //     });

                    progress(0);
                    const formData = new FormData();
                    formData.append("chunk", "0"); //断点传输
                    formData.append("chunks", "1");
                    formData.append("file", blobInfo.blob());
                    uploadFile(formData)
                        .then(response => {
                            console.info("11111111")
                            console.info(response.data)
                            success(response.data);
                            progress(100);
                            return;
                        })
                        .catch(() => {
                            failure("上传出错");
                        });
                }
            });
        },
        destroyTinymce() {
            if (window.tinymce.get(this.tinymceId)) {
                window.tinymce.get(this.tinymceId).destroy();
            }
        },
        setContent(value) {
            window.tinymce.get(this.tinymceId).setContent(value);
        },
        getContent() {
            window.tinymce.get(this.tinymceId).getContent();
        },
        clearContent() {
            window.tinymce.get(this.tinymceId).setContent("");
        }
    }
};
</script>

<style scoped>
.tinymce-container {
    position: relative;
}
.tinymce-container >>> .mce-fullscreen {
    z-index: 10000;
}
.tinymce-textarea {
    visibility: hidden;
    z-index: -1;
}
.editor-custom-btn-container {
    position: absolute;
    right: 4px;
    top: 4px;
    /*z-index: 2005;*/
}
.fullscreen .editor-custom-btn-container {
    z-index: 10000;
    position: fixed;
}
.editor-upload-btn {
    display: inline-block;
}
</style>
