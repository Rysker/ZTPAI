import React, {useCallback, useState} from 'react';
import {PieChart, Pie, Cell, ResponsiveContainer, XAxis} from 'recharts';

const CollectionPieChart = ({ data }) =>
{
    const[animation, setAnimation] = useState(true);
    const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042'];
    const RADIAN = Math.PI / 180;
    const duration = 1600;
    const renderCustomizedLabel = ({ cx, cy, midAngle, innerRadius, outerRadius, percent, index }) => {
        const radius = innerRadius + (outerRadius - innerRadius) * 0.5;
        const x = cx + radius * Math.cos(-midAngle * RADIAN);
        const y = cy + radius * Math.sin(-midAngle * RADIAN);

        const shortName = data[index].country.substring(0, 3).toUpperCase();

        return (
            <text x={x} y={y} fill="white" textAnchor={x > cx ? 'start' : 'end'} dominantBaseline="central">
                {`${shortName}`}
            </text>
        );
    };

    const onAnimationStart = useCallback(() => {
        setTimeout(() => {
            setAnimation(false)
        }, duration + 150)
    }, [])

    return (
        <ResponsiveContainer>
            <PieChart style={{ width: '100%', height: '100%' }}>
                <Pie
                    data={data}
                    dataKey="count"
                    nameKey="country"
                    cx="50%"
                    cy="50%"
                    labelLine={false}
                    label={renderCustomizedLabel}
                    outerRadius={'80%'}
                    fill="#8884d8"
                    stroke="none"
                    isAnimationActive={animation}
                    animationDuration={duration}
                    onAnimationStart={() => onAnimationStart}
                    onClick={() => {}}
                >
                    {data.map((entry, index) => (
                        <Cell
                            key={`cell-${entry.country}`}
                            fill={COLORS[index % COLORS.length]}
                        />
                    ))}
                </Pie>
            </PieChart>
        </ResponsiveContainer>
    );
};

export default CollectionPieChart;