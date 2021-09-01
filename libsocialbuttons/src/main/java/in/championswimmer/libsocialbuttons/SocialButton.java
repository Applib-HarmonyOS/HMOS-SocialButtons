package in.championswimmer.libsocialbuttons;

import ohos.agp.colors.RgbColor;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.element.Element;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.render.Canvas;
import ohos.agp.render.Paint;
import ohos.agp.render.PixelMapHolder;
import ohos.agp.utils.Color;
import ohos.app.Context;
import ohos.global.resource.NotExistException;
import ohos.global.resource.Resource;
import ohos.global.resource.ResourceManager;
import ohos.global.resource.WrongTypeException;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.media.image.PixelMap;

import java.io.IOException;
import java.util.Optional;

/**
 * SocialButton class.
 * Created by talat on 08-07-2016
 */
public class SocialButton extends Button implements Component.DrawTask {

    /**
     * SocialButton functionality.
     */
    private static final String TAG = SocialButton.class.getSimpleName();

    /**
     * DOMAIN value.
     */
    private static final int DOMAIN = 0xD000100;

    /**
     * HiLogLabel LABEL.
     */
    private static final HiLogLabel LABEL = new HiLogLabel(
            HiLog.LOG_APP, DOMAIN, TAG);

    /**
     * DEFAULT_SOCIAL.
     */
    private static final String DEFAULT_SOCIAL = "facebook";

    /**
     * MSB_SOCIAL.
     */
    private static final String MSB_SOCIAL = "msb_social";

    /**
     * mRed.
     */
    private int mRed = 3;

    /**
     * mGreen.
     */
    private int mGreen = 36;
    /**
     * mBlue.
     */
    private int mBlue = 252;

    /**
     * imageType.
     */
    private int imageType = 0;

    /**
     * pixelMap.
     */
    private PixelMap pixelMap;

    /**
     * pixelMapPaint.
     */
    private final Paint pixelMapPaint = new Paint();

    /**
     * pixelMapping.
     */
    private Optional<PixelMap> pixelMapping;

    /**
     * SocialButton constructor.
     *
     * @param context context
     */
    public SocialButton(Context context) {
        super(context);
    }

    /**
     * SocialButton constructor.
     *
     * @param context context
     * @param attrSet attrSet
     */
    public SocialButton(Context context, AttrSet attrSet) {
        super(context, attrSet);
        initAttr(attrSet);
        addDrawTask(this);
    }

    /**
     * initAttr.
     *
     * @param context context
     * @param attrs   attrs
     */
    private void initAttr(AttrSet attrs) {
        initAttributes(attrs);
        setPaintColor();
        drawBitmap();
    }

    /**
     * To set color.
     */
    private void setPaintColor() {
        pixelMapPaint.setColor(Color.WHITE);
    }

    /**
     * To draw Bitmap.
     */
    private void drawBitmap() {
        checkImageType();
        pixelMapping.ifPresent(map -> this.pixelMap = map);
    }

    /**
     * To check Image type for each Social button.
     */
    private void checkImageType() {
        switch (imageType) {
            case 0:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_logo_facebook);
                break;
            case 1:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_logo_whatsapp);
                break;
            case 2:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_logo_twitter);
                break;
            case 3:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_logo_linkedin);
                break;
            case 4:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_logo_youtube);
                break;
            case 5:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_logo_dropbox);
                break;
            case 6:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_logo_instagram);
                break;
            case 7:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_logo_quora);
                break;
            case 10:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_logo_flickr);
                break;
            case 11:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_logo_vine);
                break;
            case 12:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_logo_pinterest);
                break;
            case 13:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_logo_tumblr);
                break;
            case 14:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_logo_odnoklassniki);
                break;
            case 15:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_logo_vkontakte);
                break;
            case 16:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_logo_foursquare);
                break;
            case 17:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_logo_google);
                break;
            default:
                pixelMapping = getPixelMapByResId(ResourceTable.Media_logo_facebook);
        }
    }

    /**
     * To get PixelMaps by respective ResouceId.
     *
     * @param resourceId resourceId
     * @return Optional
     */
    private Optional<PixelMap> getPixelMapByResId(final int resourceId) {
        final ResourceManager resourceManager = getContext().getResourceManager();
        Optional<PixelMap> pixelMappingOptional = Optional.empty();
        if (resourceManager == null) {
            return Optional.empty();
        }
        try (Resource resource = resourceManager.getResource(resourceId)) {
            if (resource == null) {
                HiLog.error(LABEL, "get pixelmap failed, get resource by id is null");
                return Optional.empty();
            }
            pixelMappingOptional = Utils.preparePixelmap(resource);
        } catch (NotExistException e) {
            HiLog.error(LABEL, "close output failed NotExistException");
        } catch (IOException e) {
            HiLog.error(LABEL, "close output failed IOException");
        }
        return pixelMappingOptional;
    }

    /**
     * To set attribute values to SocialButtons.
     *
     * @param attrs attrs
     */
    private void initAttributes(AttrSet attrs) {
        String social = (attrs.getAttr(MSB_SOCIAL).isPresent() ? attrs.getAttr(MSB_SOCIAL).get().getStringValue() :
                DEFAULT_SOCIAL);
        try {
            if (social.equalsIgnoreCase(DEFAULT_SOCIAL)) {
                mRed = (int) getResourceManager().getElement(ResourceTable.Integer_facebook_red)
                        .getInteger();
                mGreen = (int) getResourceManager().getElement(ResourceTable.Integer_facebook_green)
                        .getInteger();
                mBlue = (int) getResourceManager().getElement(ResourceTable.Integer_facebook_blue)
                        .getInteger();
                imageType = 0;
                this.invalidate();
            } else if (social.equalsIgnoreCase("whatsapp")) {
                mRed = (int) getResourceManager().getElement(ResourceTable.Integer_whatsapp_red)
                        .getInteger();
                mGreen = (int) getResourceManager().getElement(ResourceTable.Integer_whatsapp_green)
                        .getInteger();
                mBlue = (int) getResourceManager().getElement(ResourceTable.Integer_whatsapp_blue)
                        .getInteger();
                imageType = 1;
                this.invalidate();
            } else if (social.equalsIgnoreCase("twitter")) {
                mRed = (int) getResourceManager().getElement(ResourceTable.Integer_twitter_red)
                        .getInteger();
                mGreen = (int) getResourceManager().getElement(ResourceTable.Integer_twitter_green)
                        .getInteger();
                mBlue = (int) getResourceManager().getElement(ResourceTable.Integer_twitter_blue)
                        .getInteger();
                imageType = 2;
                this.invalidate();
            } else if (social.equalsIgnoreCase("linkedin")) {
                mRed = (int) getResourceManager().getElement(ResourceTable.Integer_linkedin_red)
                        .getInteger();
                mGreen = (int) getResourceManager().getElement(ResourceTable.Integer_linkedin_green)
                        .getInteger();
                mBlue = (int) getResourceManager().getElement(ResourceTable.Integer_linkedin_blue)
                        .getInteger();
                imageType = 3;
                this.invalidate();
            } else if (social.equalsIgnoreCase("youtube")) {
                mRed = (int) getResourceManager().getElement(ResourceTable.Integer_youtube_red)
                        .getInteger();
                mGreen = (int) getResourceManager().getElement(ResourceTable.Integer_youtube_green)
                        .getInteger();
                mBlue = (int) getResourceManager().getElement(ResourceTable.Integer_youtube_blue)
                        .getInteger();
                imageType = 4;
                this.invalidate();
            } else if (social.equalsIgnoreCase("dropbox")) {
                mRed = (int) getResourceManager().getElement(ResourceTable.Integer_dropbox_red)
                        .getInteger();
                mGreen = (int) getResourceManager().getElement(ResourceTable.Integer_dropbox_green)
                        .getInteger();
                mBlue = (int) getResourceManager().getElement(ResourceTable.Integer_dropbox_blue)
                        .getInteger();
                imageType = 5;
                this.invalidate();
            } else if (social.equalsIgnoreCase("instagram")) {
                mRed = (int) getResourceManager().getElement(ResourceTable.Integer_instagram_red)
                        .getInteger();
                mGreen = (int) getResourceManager().getElement(ResourceTable.Integer_instagram_green)
                        .getInteger();
                mBlue = (int) getResourceManager().getElement(ResourceTable.Integer_instagram_blue)
                        .getInteger();
                imageType = 6;
                this.invalidate();
            } else if (social.equalsIgnoreCase("quora")) {
                mRed = (int) getResourceManager().getElement(ResourceTable.Integer_quora_red)
                        .getInteger();
                mGreen = (int) getResourceManager().getElement(ResourceTable.Integer_quora_green)
                        .getInteger();
                mBlue = (int) getResourceManager().getElement(ResourceTable.Integer_quora_blue)
                        .getInteger();
                imageType = 7;
                this.invalidate();
            }
            checkOne(social);
        } catch (IOException | NotExistException | WrongTypeException e) {
            e.printStackTrace();
        }
    }

    /**
     * to reduce cognitive complexity.
     */
    private void checkOne(String social) {
        try {
            if (social.equalsIgnoreCase("flickr")) {
                mRed = (int) getResourceManager().getElement(ResourceTable.Integer_flickr_red)
                        .getInteger();
                mGreen = (int) getResourceManager().getElement(ResourceTable.Integer_flickr_green)
                        .getInteger();
                mBlue = (int) getResourceManager().getElement(ResourceTable.Integer_flickr_blue)
                        .getInteger();
                imageType = 10;
                this.invalidate();
            } else if (social.equalsIgnoreCase("vine")) {
                mRed = (int) getResourceManager().getElement(ResourceTable.Integer_vine_red)
                        .getInteger();
                mGreen = (int) getResourceManager().getElement(ResourceTable.Integer_vine_green)
                        .getInteger();
                mBlue = (int) getResourceManager().getElement(ResourceTable.Integer_vine_blue)
                        .getInteger();
                imageType = 11;
                this.invalidate();
            } else if (social.equalsIgnoreCase("pinterest")) {
                mRed = (int) getResourceManager().getElement(ResourceTable.Integer_pinterest_red)
                        .getInteger();
                mGreen = (int) getResourceManager().getElement(ResourceTable.Integer_pinterest_green)
                        .getInteger();
                mBlue = (int) getResourceManager().getElement(ResourceTable.Integer_pinterest_blue)
                        .getInteger();
                imageType = 12;
                this.invalidate();
            } else if (social.equalsIgnoreCase("tumblr")) {
                mRed = (int) getResourceManager().getElement(ResourceTable.Integer_tumblr_red)
                        .getInteger();
                mGreen = (int) getResourceManager().getElement(ResourceTable.Integer_tumblr_green)
                        .getInteger();
                mBlue = (int) getResourceManager().getElement(ResourceTable.Integer_tumblr_blue)
                        .getInteger();
                imageType = 13;
                this.invalidate();
            } else if (social.equalsIgnoreCase("odnoklassniki")) {
                mRed = (int) getResourceManager().getElement(ResourceTable.Integer_odnoklassniki_red)
                        .getInteger();
                mGreen = (int) getResourceManager().getElement(ResourceTable.Integer_odnoklassniki_green)
                        .getInteger();
                mBlue = (int) getResourceManager().getElement(ResourceTable.Integer_odnoklassniki_blue)
                        .getInteger();
                imageType = 14;
                this.invalidate();
            } else if (social.equalsIgnoreCase("vkontakte")) {
                mRed = (int) getResourceManager().getElement(ResourceTable.Integer_vkontakte_red)
                        .getInteger();
                mGreen = (int) getResourceManager().getElement(ResourceTable.Integer_vkontakte_green)
                        .getInteger();
                mBlue = (int) getResourceManager().getElement(ResourceTable.Integer_vkontakte_blue)
                        .getInteger();
                imageType = 15;
                this.invalidate();
            } else if (social.equalsIgnoreCase("foursquare")) {
                mRed = (int) getResourceManager().getElement(ResourceTable.Integer_foursquare_red)
                        .getInteger();
                mGreen = (int) getResourceManager().getElement(ResourceTable.Integer_foursquare_green)
                        .getInteger();
                mBlue = (int) getResourceManager().getElement(ResourceTable.Integer_foursquare_blue)
                        .getInteger();
                imageType = 16;
                this.invalidate();
            } else if (social.equalsIgnoreCase("google")) {
                mRed = (int) getResourceManager().getElement(ResourceTable.Integer_googleplus_red)
                        .getInteger();
                mGreen = (int) getResourceManager().getElement(ResourceTable.Integer_googleplus_green)
                        .getInteger();
                mBlue = (int) getResourceManager().getElement(ResourceTable.Integer_googleplus_blue)
                        .getInteger();
                imageType = 17;
                this.invalidate();
            }
        } catch (IOException | NotExistException | WrongTypeException e) {
            e.printStackTrace();
        }
    }

    /**
     * OnDraw method to set color and margins.
     *
     * @param component component
     * @param canvas    canvas
     */
    @Override
    public void onDraw(Component component, Canvas canvas) {
        this.setBackground(setBackgroundColor());
        setTextColor(Color.WHITE);
        setHeight(200);
        setMarginBottom(10);
        canvas.drawPixelMapHolder(
                new PixelMapHolder(pixelMap), (float) getWidth() / 10, (float) getHeight() / 5, pixelMapPaint);
    }

    /**
     * To set Background color.
     *
     * @return Element
     */
    public Element setBackgroundColor() {
        RgbColor rgbColor = new RgbColor(mRed, mGreen, mBlue);
        ShapeElement shapeElement = new ShapeElement();
        shapeElement.setRgbColor(rgbColor);
        return shapeElement;
    }
}