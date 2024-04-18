package com.jerry.jetpack_navigation.ui.shape

import android.graphics.PointF

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density


private const val CURVE_CIRCLE_RADIUS = 56
// the coordinates of the first curve
private val mFirstCurveStartPoint = PointF()
private val mFirstCurveControlPoint1 = PointF()
private val mFirstCurveControlPoint2 = PointF()
private val mFirstCurveEndPoint = PointF()

private val mSecondCurveControlPoint1 = PointF()
private val mSecondCurveControlPoint2 = PointF()
private var mSecondCurveStartPoint = PointF()
private var mSecondCurveEndPoint = PointF()

class BottomNavItemCurvedShape(
    private val curveCircleRadius: Int = CURVE_CIRCLE_RADIUS,
    private val applyCurve: Boolean = true
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: androidx.compose.ui.unit.LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(path = Path().apply {
            val curveDepth = (curveCircleRadius / 0.9).toFloat()
            // the coordinates (x,y) of the start point before curve
            mFirstCurveStartPoint.set(
                (size.width / 2) - (curveCircleRadius * 2.0).toFloat(),
                curveDepth
            )

            // the coordinates (x,y) of the end point after curve
            mFirstCurveEndPoint.set(
                size.width / 2,
                if (applyCurve) {
                    0F
                } else {
                    curveDepth
                }
            )

            // same thing for the second curve
            mSecondCurveStartPoint =
                mFirstCurveEndPoint
            mSecondCurveEndPoint.set(
                (size.width / 2) + (curveCircleRadius * 2.0).toFloat(),
                curveDepth
            )

            // the coordinates (x,y)  of the 1st control point on a cubic curve
            mFirstCurveControlPoint1.set(
                mFirstCurveStartPoint.x + curveDepth,
                mFirstCurveStartPoint.y
            )

            // the coordinates (x,y)  of the 2nd control point on a cubic curve
            mFirstCurveControlPoint2.set(
                mFirstCurveEndPoint.x - (curveCircleRadius * 2.4).toFloat() + curveCircleRadius,
                mFirstCurveEndPoint.y
            )
            mSecondCurveControlPoint1.set(
                mSecondCurveStartPoint.x + (curveCircleRadius * 2.4).toFloat() - curveCircleRadius,
                mSecondCurveStartPoint.y
            )
            mSecondCurveControlPoint2.set(
                mSecondCurveEndPoint.x - (curveDepth),
                mSecondCurveEndPoint.y
            )

            moveTo(0f, curveDepth)
            lineTo(mFirstCurveStartPoint.x, mFirstCurveStartPoint.y)
            cubicTo(
                mFirstCurveControlPoint1.x, mFirstCurveControlPoint1.y,
                mFirstCurveControlPoint2.x, mFirstCurveControlPoint2.y,
                mFirstCurveEndPoint.x, mFirstCurveEndPoint.y
            )
            cubicTo(
                mSecondCurveControlPoint1.x, mSecondCurveControlPoint1.y,
                mSecondCurveControlPoint2.x, mSecondCurveControlPoint2.y,
                mSecondCurveEndPoint.x, mSecondCurveEndPoint.y
            )
            lineTo(size.width, curveDepth)
            lineTo(size.width, size.height)
            lineTo(0F, (size.height * 1.1).toFloat())
        })
    }
}