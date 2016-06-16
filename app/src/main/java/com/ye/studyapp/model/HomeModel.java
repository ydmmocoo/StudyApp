package com.ye.studyapp.model;

import java.util.List;

/**
 * Created by admin on 2016/6/14.
 */

public class HomeModel {

    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyEntity showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyEntity getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyEntity showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyEntity {

        private PagebeanEntity pagebean;
        private int ret_code;

        public PagebeanEntity getPagebean() {
            return pagebean;
        }

        public void setPagebean(PagebeanEntity pagebean) {
            this.pagebean = pagebean;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public static class PagebeanEntity {

            private int allNum;
            private int allPages;
            private int currentPage;
            private int maxResult;
            private List<ContentlistEntity> contentlist;

            public int getAllNum() {
                return allNum;
            }

            public void setAllNum(int allNum) {
                this.allNum = allNum;
            }

            public int getAllPages() {
                return allPages;
            }

            public void setAllPages(int allPages) {
                this.allPages = allPages;
            }

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public int getMaxResult() {
                return maxResult;
            }

            public void setMaxResult(int maxResult) {
                this.maxResult = maxResult;
            }

            public List<ContentlistEntity> getContentlist() {
                return contentlist;
            }

            public void setContentlist(List<ContentlistEntity> contentlist) {
                this.contentlist = contentlist;
            }

            public static class ContentlistEntity {

                private String channelId;
                private String channelName;
                private String content;
                private String desc;
                private String html;
                private String link;
                private String nid;
                private String pubDate;
                private int sentiment_display;
                private SentimentTagEntity sentiment_tag;
                private String source;
                private String title;
                private List<ImageurlsEntity> imageurls;

                public String getChannelId() {
                    return channelId;
                }

                public void setChannelId(String channelId) {
                    this.channelId = channelId;
                }

                public String getChannelName() {
                    return channelName;
                }

                public void setChannelName(String channelName) {
                    this.channelName = channelName;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getHtml() {
                    return html;
                }

                public void setHtml(String html) {
                    this.html = html;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getNid() {
                    return nid;
                }

                public void setNid(String nid) {
                    this.nid = nid;
                }

                public String getPubDate() {
                    return pubDate;
                }

                public void setPubDate(String pubDate) {
                    this.pubDate = pubDate;
                }

                public int getSentiment_display() {
                    return sentiment_display;
                }

                public void setSentiment_display(int sentiment_display) {
                    this.sentiment_display = sentiment_display;
                }

                public SentimentTagEntity getSentiment_tag() {
                    return sentiment_tag;
                }

                public void setSentiment_tag(SentimentTagEntity sentiment_tag) {
                    this.sentiment_tag = sentiment_tag;
                }

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public List<ImageurlsEntity> getImageurls() {
                    return imageurls;
                }

                public void setImageurls(List<ImageurlsEntity> imageurls) {
                    this.imageurls = imageurls;
                }

                public static class SentimentTagEntity {

                    private String count;
                    private String dim;
                    private String id;
                    private int isbooked;
                    private String ishot;
                    private String name;
                    private String type;

                    public String getCount() {
                        return count;
                    }

                    public void setCount(String count) {
                        this.count = count;
                    }

                    public String getDim() {
                        return dim;
                    }

                    public void setDim(String dim) {
                        this.dim = dim;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public int getIsbooked() {
                        return isbooked;
                    }

                    public void setIsbooked(int isbooked) {
                        this.isbooked = isbooked;
                    }

                    public String getIshot() {
                        return ishot;
                    }

                    public void setIshot(String ishot) {
                        this.ishot = ishot;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }
                }

                public static class ImageurlsEntity {

                    private int height;
                    private String url;
                    private int width;

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }
                }
            }
        }
    }
}
